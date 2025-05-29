package org.groomUniv.meet.oauth.service;

import lombok.RequiredArgsConstructor;
import org.groomUniv.meet.common.security.JwtToken;
import org.groomUniv.meet.common.security.JwtTokenProvider;
import org.groomUniv.meet.common.security.RefreshTokenService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final RedisTemplate<String, String> redisTemplate;
    private final UserDetailsService userDetailsService;

    // 토큰 재발급
    public JwtToken reissue(String refreshToken){
        // 토큰 유효성 검사 (validateToken())
        if(!jwtTokenProvider.validateToken(refreshToken)){
            throw new IllegalArgumentException("Invalid refresh token");
        }

        // refreshToken에서 username 추출(subject에 저장된 username)
        String username = jwtTokenProvider.getUsername(refreshToken);

        // redis에 저장된 refreshToken과 일치하는지 확인
        String storedRefreshToken = refreshTokenService.getRefreshToken(username);
        if(storedRefreshToken == null || !storedRefreshToken.equals(refreshToken)){
            throw new IllegalArgumentException("Invalid refresh token");
        }

        // 유저 정보를 기반으로 인증 객체 생성 (이미 인증된 유저이기 때문에 비밀번호는 필요 없음)
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());

        return jwtTokenProvider.generateToken(authentication);
    }
}
