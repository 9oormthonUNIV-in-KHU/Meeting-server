package org.groomUniv.meet.common.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = resolveToken((HttpServletRequest) servletRequest);  // header에서 jwt 토큰을 추출

        if (token != null && jwtTokenProvider.validateToken(token)) {   // 토큰이 존재하고 유효할 경우
            Authentication authentication = jwtTokenProvider.getAuthentication(token);  // 토큰에서 Authentication 객체를 얻어
            SecurityContextHolder.getContext().setAuthentication(authentication);   // Spring SecurityContext에 저장
        }
        filterChain.doFilter(servletRequest, servletResponse);  // 다음 필터로 요청을 전달(이걸 호출하지 않으면 요청이 중간에서 끊겨버려서 다음 단계 진행 불가)
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");    // 헤더에서 Authorization 값을 추출
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);     // "Bearer " 이후의 문자열만 반환
        }
        return null;
    }
}
