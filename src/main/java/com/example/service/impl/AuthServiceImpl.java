package com.example.service.impl;

import com.example.dto.LoginRequest;
import com.example.dto.LoginResponse;
import com.example.dto.RegisterRequest;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.AuthService;
import com.example.service.EmailService;
import com.example.service.RedisService;
import com.example.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceImpl implements AuthService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private RedisService redisService;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    private static final String CODE_PREFIX = "register:code:";
    private static final long CODE_EXPIRE_TIME = 10;
    
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Override
    public void sendVerificationCode(String email) {
        String code = generateCode();
        String redisKey = CODE_PREFIX + email;
        
        redisService.set(redisKey, code, CODE_EXPIRE_TIME, TimeUnit.MINUTES);
        
        emailService.sendVerificationCode(email, code);
    }
    
    @Override
    @Transactional
    public void register(RegisterRequest request) {
        String redisKey = CODE_PREFIX + request.getEmail();
        Object storedCode = redisService.get(redisKey);
        
        if (storedCode == null) {
            throw new RuntimeException("验证码已过期，请重新获取");
        }
        
        if (!storedCode.toString().equals(request.getCode())) {
            throw new RuntimeException("验证码错误");
        }
        
        User existingUser = userMapper.selectByUsernameExact(request.getUsername());
        if (existingUser != null) {
            throw new RuntimeException("用户名已存在");
        }
        
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setStatus(1);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        
        userMapper.insert(user);
        
        redisService.delete(redisKey);
    }
    
    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userMapper.selectByUsernameExact(request.getUsername());
        
        if (user == null) {
            throw new RuntimeException("用户名或密码错误");
        }
        
        if (user.getStatus() == 0) {
            throw new RuntimeException("账号已被禁用");
        }
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }
        
        String token = jwtUtil.generateToken(user.getUsername(), user.getId());
        
        return new LoginResponse(token, "Bearer", 86400L);
    }
    
    private String generateCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);
    }
}
