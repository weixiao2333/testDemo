package com.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "刷新Token请求")
public class RefreshTokenRequest {
    
    @Schema(description = "刷新Token", example = "eyJhbGciOiJIUzI1NiJ9...")
    private String refreshToken;
}
