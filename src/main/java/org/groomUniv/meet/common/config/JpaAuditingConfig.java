package org.groomUniv.meet.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration


// Spring이 CreatedBy에서 어떤 사용자를 넣을지 모르기 때문에 그걸 이 부분을 통해 찾아주려고 적는 Config 파일
public class JpaAuditingConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        // 실제 사용자 인증이 있는 경우엔 SecurityContext에서 꺼내서 여기 부분 변경해야함 basic으로 쓰면 안됨.
        return () -> Optional.of("basic"); // 기본값
    }
}
// Spring Security 사용할 때의 SecurityContextHolder 사용법
//  return () -> Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
//                         .filter(Authentication::isAuthenticated)
//                         .map(Authentication::getName);