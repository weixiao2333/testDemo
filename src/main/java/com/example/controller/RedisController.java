package com.example.controller;

import com.example.common.Result;
import com.example.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @PostMapping("/set")
    public Result<String> set(@RequestParam String key, @RequestParam String value) {
        try {
            redisService.set(key, value);
            return Result.success("设置成功");
        } catch (Exception e) {
            return Result.error("设置失败: " + e.getMessage());
        }
    }

    @PostMapping("/setWithExpiry")
    public Result<String> setWithExpiry(@RequestParam String key, 
                                        @RequestParam String value,
                                        @RequestParam long timeout) {
        try {
            redisService.set(key, value, timeout, TimeUnit.SECONDS);
            return Result.success("设置成功，过期时间: " + timeout + "秒");
        } catch (Exception e) {
            return Result.error("设置失败: " + e.getMessage());
        }
    }

    @GetMapping("/get")
    public Result<Object> get(@RequestParam String key) {
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

    @DeleteMapping("/delete")
    public Result<String> delete(@RequestParam String key) {
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

    @GetMapping("/hasKey")
    public Result<Boolean> hasKey(@RequestParam String key) {
        try {
            Boolean exists = redisService.hasKey(key);
            return Result.success(exists);
        } catch (Exception e) {
            return Result.error("查询失败: " + e.getMessage());
        }
    }

    @PostMapping("/expire")
    public Result<String> expire(@RequestParam String key, @RequestParam long timeout) {
        try {
            redisService.expire(key, timeout, TimeUnit.SECONDS);
            return Result.success("设置过期时间成功");
        } catch (Exception e) {
            return Result.error("设置过期时间失败: " + e.getMessage());
        }
    }
}
