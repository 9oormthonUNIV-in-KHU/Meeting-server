package org.groomUniv.meet.common.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RedisTemplate<String, String> redisTemplate;

    @Value("${jwt.refresh-expiration-time}")
    private long refreshTokenExpirationTime;

    private String getKey(String username){
        return "refresh_token:" + username;
    }

    public void saveRefreshToken(String username, String refreshToken) {
        redisTemplate.opsForValue().set(
                getKey(username),
                refreshToken,
                refreshTokenExpirationTime
        );
    }

    public String getRefreshToken(String username) {
        return redisTemplate.opsForValue().get(getKey(username));
    }

    public void deleteRefreshToken(String username) {
        redisTemplate.delete(getKey(username));
    }
}
