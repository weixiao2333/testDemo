package com.example.controller;

import com.example.common.Result;
import com.example.dto.LoginRequest;
import com.example.dto.LoginResponse;
import com.example.dto.RegisterRequest;
import com.example.dto.SendCodeRequest;
import com.example.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "认证管理接口", description = "用户登录、注册、验证码相关接口")
@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    @Operation(summary = "发送注册验证码", description = "向指定邮箱发送6位数字验证码，有效期10分钟")
    @PostMapping("/send-code")
    public Result<String> sendVerificationCode(@Valid @RequestBody SendCodeRequest request) {
        try {
            authService.sendVerificationCode(request.getEmail());
            return Result.success("验证码已发送到邮箱");
        } catch (Exception e) {
            return Result.error("发送验证码失败: " + e.getMessage());
        }
    }
    
    @Operation(summary = "用户注册", description = "使用用户名、密码、邮箱和验证码进行注册")
    @PostMapping("/register")
    public Result<String> register(@Valid @RequestBody RegisterRequest request) {
        try {
            authService.register(request);
            return Result.success("注册成功");
        } catch (RuntimeException e) {
            return Result.error(400, e.getMessage());
        } catch (Exception e) {
            return Result.error("注册失败: " + e.getMessage());
        }
    }
    
    @Operation(summary = "用户登录", description = "使用用户名和密码登录，返回JWT Token")
    @PostMapping("/login")
    public Result<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        try {
            LoginResponse response = authService.login(request);
            return Result.success("登录成功", response);
        } catch (RuntimeException e) {
            return Result.error(401, e.getMessage());
        } catch (Exception e) {
            return Result.error("登录失败: " + e.getMessage());
        }
    }
}
