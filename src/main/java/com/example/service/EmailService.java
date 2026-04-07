package com.example.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Value("${spring.mail.username:}")
    private String fromEmail;
    
    public void sendVerificationCode(String toEmail, String code) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setSubject("注册验证码");
            
            String htmlContent = "<div style='padding: 20px; font-family: Arial, sans-serif;'>" +
                    "<h2 style='color: #333;'>欢迎注册</h2>" +
                    "<p>您的验证码是：</p>" +
                    "<div style='background-color: #f5f5f5; padding: 15px; border-radius: 5px; " +
                    "text-align: center; font-size: 24px; font-weight: bold; color: #007bff; " +
                    "letter-spacing: 5px; margin: 20px 0;'>" + code + "</div>" +
                    "<p style='color: #666;'>验证码有效期为 10 分钟，请尽快使用。</p>" +
                    "<p style='color: #999; font-size: 12px;'>如果这不是您本人的操作，请忽略此邮件。</p>" +
                    "</div>";
            
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("发送邮件失败: " + e.getMessage());
        }
    }
}
