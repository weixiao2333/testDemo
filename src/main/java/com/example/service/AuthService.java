package com.example.service;

import com.example.dto.LoginRequest;
import com.example.dto.LoginResponse;
import com.example.dto.RegisterRequest;

public interface AuthService {
    
    void sendVerificationCode(String email);
    
    LoginResponse login(LoginRequest request);
    
    void register(RegisterRequest request);
}
