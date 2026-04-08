package com.example.controller;

import com.example.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "受保护资源接口", description = "需要JWT Token才能访问的接口")
@RestController
@RequestMapping("/protected")
public class ProtectedController {
    
    @Operation(summary = "获取受保护资源", description = "此接口需要有效的JWT Token才能访问")
    @GetMapping("/data")
    public Result<String> getProtectedData(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        Long userId = (Long) request.getAttribute("userId");
        
        return Result.success("这是受保护的数据，当前用户: " + username + " (ID: " + userId + ")");
    }
}
