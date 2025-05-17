package org.groomUniv.meet.common.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    private static final List<String> NO_AUTH_PATHS = List.of(
            "/api/member/signup",
            "/api/member/login"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getServletPath();
        // 인증이 필요 없는 경로는 필터 패스
        if (NO_AUTH_PATHS.contains(path)) {
            log.info("No auth needed for path: {}", path);
            filterChain.doFilter(request, response);
            return;
        }

        String token = resolveToken(request);  // header에서 jwt 토큰을 추출
        if (token != null) {   // 토큰이 존재하고 유효할 경우
            if(jwtTokenProvider.validateToken(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);  // 토큰에서 Authentication 객체를 얻어
                SecurityContextHolder.getContext().setAuthentication(authentication);   // Spring SecurityContext에 저장
            }
            else{
                // 필터는 DispatcherServlet 이전에 동작하므로, 직접 JSON 형태로 응답을 만들어서 반환해야 함
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);    // 유효하지 않은 토큰: 401 Unauthorized 반환
                response.setContentType("application/json;charset=UTF-8");  // 응답의 ContentType을 json으로 지정
                response.getWriter().write("{\"error\": \"Invalid JWT token\"}");    // json 형식의 에러 메시지
                return; // 요청 중단, 다음 필터로 전달하지 않음
            }

        }
        filterChain.doFilter(request, response);  // 다음 필터로 요청을 전달(이걸 호출하지 않으면 요청이 중간에서 끊겨버려서 다음 단계 진행 불가)
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");    // 헤더에서 Authorization 값을 추출
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);     // "Bearer " 이후의 문자열만 반환
        }
        return null;
    }


}
