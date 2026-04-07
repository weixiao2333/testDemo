package com.example.controller;

import com.example.common.Result;
import com.example.service.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@Tag(name = "Redis缓存接口", description = "Redis缓存的增删改查相关接口")
@RestController
@RequestMapping("/api/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @Operation(summary = "设置缓存", description = "向Redis中设置一个键值对（永久有效）")
    @PostMapping("/set")
    public Result<String> set(
            @Parameter(description = "缓存键", example = "username")
            @RequestParam String key,
            @Parameter(description = "缓存值", example = "张三")
            @RequestParam String value) {
        try {
            redisService.set(key, value);
            return Result.success("设置成功");
        } catch (Exception e) {
            return Result.error("设置失败: " + e.getMessage());
        }
    }

    @Operation(summary = "设置带过期时间的缓存", description = "向Redis中设置一个键值对，并指定过期时间（秒）")
    @PostMapping("/setWithExpiry")
    public Result<String> setWithExpiry(
            @Parameter(description = "缓存键", example = "token")
            @RequestParam String key,
            @Parameter(description = "缓存值", example = "abc123xyz")
            @RequestParam String value,
            @Parameter(description = "过期时间（秒）", example = "3600")
            @RequestParam long timeout) {
        try {
            redisService.set(key, value, timeout, TimeUnit.SECONDS);
            return Result.success("设置成功，过期时间: " + timeout + "秒");
        } catch (Exception e) {
            return Result.error("设置失败: " + e.getMessage());
        }
    }

    @Operation(summary = "获取缓存", description = "根据键从Redis中获取对应的值")
    @GetMapping("/get")
    public Result<Object> get(
            @Parameter(description = "缓存键", example = "username")
            @RequestParam String key) {
        try {
            Object value = redisService.get(key);
            if (value != null) {
                return Result.success(value);
            } else {
                return Result.error(404, "键不存在");
            }
        } catch (Exception e) {
            return Result.error("获取失败: " + e.getMessage());
        }
    }

    @Operation(summary = "删除缓存", description = "根据键从Redis中删除对应的键值对")
    @DeleteMapping("/delete")
    public Result<String> delete(
            @Parameter(description = "缓存键", example = "username")
            @RequestParam String key) {
        try {
            Boolean deleted = redisService.delete(key);
            if (deleted) {
                return Result.success("删除成功");
            } else {
                return Result.error(404, "键不存在");
            }
        } catch (Exception e) {
            return Result.error("删除失败: " + e.getMessage());
        }
    }

    @Operation(summary = "判断键是否存在", description = "检查指定的键是否存在于Redis中")
    @GetMapping("/hasKey")
    public Result<Boolean> hasKey(
            @Parameter(description = "缓存键", example = "username")
            @RequestParam String key) {
        try {
            Boolean exists = redisService.hasKey(key);
            return Result.success(exists);
        } catch (Exception e) {
            return Result.error("查询失败: " + e.getMessage());
        }
    }

    @Operation(summary = "设置过期时间", description = "为已存在的键设置过期时间（秒）")
    @PostMapping("/expire")
    public Result<String> expire(
            @Parameter(description = "缓存键", example = "token")
            @RequestParam String key,
            @Parameter(description = "过期时间（秒）", example = "7200")
            @RequestParam long timeout) {
        try {
            redisService.expire(key, timeout, TimeUnit.SECONDS);
            return Result.success("设置过期时间成功");
        } catch (Exception e) {
            return Result.error("设置过期时间失败: " + e.getMessage());
        }
    }
}
