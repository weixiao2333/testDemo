package com.example.service;

public interface RefreshTokenService {

    void saveRefreshToken(Long userId, String refreshToken, long expirationSeconds);

    String getRefreshToken(Long userId);

    boolean validateRefreshToken(Long userId, String refreshToken);

    void removeRefreshToken(Long userId);
}
