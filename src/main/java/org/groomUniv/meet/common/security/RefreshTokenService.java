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

    private String getKey(String userId){
        return "refresh_token:" + userId;
    }

    public void saveRefreshToken(String userId, String refreshToken) {
        redisTemplate.opsForValue().set(
                getKey(userId),
                refreshToken,
                refreshTokenExpirationTime
        );
    }

    public String getRefreshToken(String userId) {
        return redisTemplate.opsForValue().get(getKey(userId));
    }

    public void deleteRefreshToken(String userId) {
        redisTemplate.delete(getKey(userId));
    }
}
