package com.example.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Schema(description = "用户实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "用户ID", example = "1")
    private Long id;
    
    @Schema(description = "用户名", example = "zhangsan")
    private String username;
    
    @Schema(description = "邮箱地址", example = "zhangsan@example.com")
    private String email;
    
    @Schema(description = "密码", example = "123456")
    private String password;
    
    @Schema(description = "状态（0-禁用，1-启用）", example = "1")
    private Integer status;
    
    @Schema(description = "创建时间", example = "2024-01-01T10:00:00")
    private LocalDateTime createTime;
    
    @Schema(description = "更新时间", example = "2024-01-01T10:00:00")
    private LocalDateTime updateTime;
}
