package com.example.controller;

import com.example.common.Result;
import com.example.entity.User;
import com.example.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "用户管理接口", description = "用户的增删改查相关接口")
@RestController
@RequestMapping("/test")
public class TestUserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Hello 测试", description = "测试 MyBatis 连接的 Hello 接口")
    @GetMapping("/hello")
    public Result<String> hello() {
        return Result.success("Hello, MyBatis!");
    }

    @Operation(summary = "获取所有用户", description = "查询数据库中的所有用户列表")
    @GetMapping("/users")
    public Result<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return Result.success(users);
        } catch (Exception e) {
            return Result.error("获取用户列表失败: " + e.getMessage());
        }
    }

    @Operation(summary = "根据ID获取用户", description = "通过用户ID查询单个用户信息")
    @GetMapping("/user/{id}")
    public Result<User> getUserById(
            @Parameter(description = "用户ID", example = "1")
            @PathVariable Long id) {
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

    @Operation(summary = "创建用户", description = "新增一个用户到数据库")
    @PostMapping("/user")
    public Result<User> createUser(
            @Parameter(description = "用户信息对象", required = true)
            @RequestBody User user) {
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

    @Operation(summary = "更新用户", description = "根据用户信息更新数据库中的记录")
    @PutMapping("/user")
    public Result<User> updateUser(
            @Parameter(description = "用户信息对象", required = true)
            @RequestBody User user) {
        try {
            User updated = userService.updateUser(user);
            return Result.success("User updated successfully", updated);
        } catch (RuntimeException e) {
            return Result.error(404, e.getMessage());
        } catch (Exception e) {
            return Result.error("Failed to update user: " + e.getMessage());
        }
    }

    @Operation(summary = "删除用户", description = "根据用户ID删除数据库中的记录")
    @DeleteMapping("/user/{id}")
    public Result<Void> deleteUser(
            @Parameter(description = "用户ID", example = "1")
            @PathVariable Long id) {
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
