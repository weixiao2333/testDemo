package com.example.service;

public interface EmailService {

    void sendVerificationCode(String toEmail, String code);
}
