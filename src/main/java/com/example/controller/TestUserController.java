package com.example.controller;

import com.example.common.Result;
import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public Result<String> hello() {
        return Result.success("Hello, MyBatis!");
    }

    @GetMapping("/users")
    public Result<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return Result.success(users);
        } catch (Exception e) {
            return Result.error("获取用户列表失败: " + e.getMessage());
        }
    }

    @GetMapping("/user/{id}")
    public Result<User> getUserById(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            if (user != null) {
                return Result.success(user);
            } else {
                return Result.error(404, "User not found");
            }
        } catch (Exception e) {
            return Result.error("获取用户信息失败: " + e.getMessage());
        }
    }

    @PostMapping("/user")
    public Result<User> createUser(@RequestBody User user) {
        try {
            User created = userService.createUser(user);
            return Result.success("User created successfully", created);
        } catch (IllegalArgumentException e) {
            return Result.error(400, e.getMessage());
        } catch (RuntimeException e) {
            return Result.error(409, e.getMessage());
        } catch (Exception e) {
            return Result.error("Failed to create user: " + e.getMessage());
        }
    }

    @PutMapping("/user")
    public Result<User> updateUser(@RequestBody User user) {
        try {
            User updated = userService.updateUser(user);
            return Result.success("User updated successfully", updated);
        } catch (RuntimeException e) {
            return Result.error(404, e.getMessage());
        } catch (Exception e) {
            return Result.error("Failed to update user: " + e.getMessage());
        }
    }

    @DeleteMapping("/user/{id}")
    public Result<Void> deleteUser(@PathVariable Long id) {
        try {
            boolean deleted = userService.deleteUser(id);
            return Result.success("User deleted successfully", null);
        } catch (RuntimeException e) {
            return Result.error(404, e.getMessage());
        } catch (Exception e) {
            return Result.error("Failed to delete user: " + e.getMessage());
        }
    }
}
