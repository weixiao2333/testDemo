package com.example.service;

import com.example.dto.LoginRequest;
import com.example.dto.LoginResponse;
import com.example.dto.RefreshTokenRequest;
import com.example.dto.RegisterRequest;
import com.example.dto.TokenResponse;

public interface AuthService {
    
    void sendVerificationCode(String email);
    
    LoginResponse login(LoginRequest request);
    
    void register(RegisterRequest request);
    
    TokenResponse refreshToken(RefreshTokenRequest request);
    
    void logout(Long userId);
}
