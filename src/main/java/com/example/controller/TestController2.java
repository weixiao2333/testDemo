package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController2 {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/hello")
    public String hello() {
        return "Hello, MyBatis!";
    }
    
    @GetMapping("/users")
    public Map<String, Object> getAllUsers() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<User> users = userService.getAllUsers();
            result.put("code", 200);
            result.put("data", users);
            result.put("message", "success");
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", e.getMessage());
        }
        return result;
    }
    
    @GetMapping("/user/{id}")
    public Map<String, Object> getUserById(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            User user = userService.getUserById(id);
            if (user != null) {
                result.put("code", 200);
                result.put("data", user);
                result.put("message", "success");
            } else {
                result.put("code", 404);
                result.put("message", "User not found");
            }
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", e.getMessage());
        }
        return result;
    }
    
    @PostMapping("/user")
    public Map<String, Object> createUser(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();
        try {
            User created = userService.createUser(user);
            result.put("code", 200);
            result.put("data", created);
            result.put("message", "User created successfully");
        } catch (IllegalArgumentException e) {
            result.put("code", 400);
            result.put("message", e.getMessage());
        } catch (RuntimeException e) {
            result.put("code", 409);
            result.put("message", e.getMessage());
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "Failed to create user: " + e.getMessage());
        }
        return result;
    }
    
    @PutMapping("/user")
    public Map<String, Object> updateUser(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();
        try {
            User updated = userService.updateUser(user);
            result.put("code", 200);
            result.put("data", updated);
            result.put("message", "User updated successfully");
        } catch (RuntimeException e) {
            result.put("code", 404);
            result.put("message", e.getMessage());
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "Failed to update user: " + e.getMessage());
        }
        return result;
    }
    
    @DeleteMapping("/user/{id}")
    public Map<String, Object> deleteUser(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean deleted = userService.deleteUser(id);
            result.put("code", 200);
            result.put("message", "User deleted successfully");
        } catch (RuntimeException e) {
            result.put("code", 404);
            result.put("message", e.getMessage());
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "Failed to delete user: " + e.getMessage());
        }
        return result;
    }
}
