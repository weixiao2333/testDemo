package com.example.controller;

import com.example.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/")
    public String index() {
        return "redirect:/index.html";
    }

    @GetMapping("/hello")
    public Result<String> hello() {
        return Result.success("Hello, World!");
    }

    @GetMapping("/info")
    public Result<String> info() {
        return Result.success("This is a test controller");
    }
}
