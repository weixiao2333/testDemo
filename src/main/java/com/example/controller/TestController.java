package com.example.controller;

import com.example.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "测试接口", description = "基础测试相关接口")
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Operation(summary = "首页重定向", description = "重定向到 index.html 页面")
    @GetMapping("/")
    public String index() {
        return "redirect:/index.html";
    }

    @Operation(summary = "Hello 接口", description = "返回 Hello, World! 消息")
    @GetMapping("/hello")
    public Result<String> hello() {
        return Result.success("Hello, World!");
    }

    @Operation(summary = "信息接口", description = "返回测试控制器的基本信息")
    @GetMapping("/info")
    public Result<String> info() {
        return Result.success("This is a test controller");
    }
}
