package com.example.service;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ???????
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }
    
    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }
    
    @Override
    public List<User> searchByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
    
    @Override
    @Transactional
    public User createUser(User user) {
        // 1. ?????
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("?????");
        }
        
        // 2. ?????????
        List<User> existing = userMapper.selectByUsername(user.getUsername());
        if (!existing.isEmpty()) {
            throw new RuntimeException("?????");
        }
        
        // 3. ????????
        if (user.getStatus() == null) {
            user.setStatus(1);
        }
        
        // 4. ???????
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        
        // 5. ???
        userMapper.insert(user);
        
        return user;
    }
    
    @Override
    @Transactional
    public User updateUser(User user) {
        // 1. ???????
        User existing = userMapper.selectById(user.getId());
        if (existing == null) {
            throw new RuntimeException("?????");
        }
        
        // 2. ???
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
        
        return userMapper.selectById(user.getId());
    }
    
    @Override
    @Transactional
    public boolean deleteUser(Long id) {
        User existing = userMapper.selectById(id);
        if (existing == null) {
            throw new RuntimeException("?????");
        }
        
        int rows = userMapper.deleteById(id);
        return rows > 0;
    }
}
