package org.groomUniv.meet.oauth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.groomUniv.meet.oauth.entity.Member;
import org.groomUniv.meet.oauth.enums.Role;

import java.util.List;

public record SignUpRequest(
        @Schema(description = "이메일", example = "abc1234@khu.ac.kr")
        String email,

        @Schema(description = "비밀번호", example = "1234")
        String password,

        @Schema(description = "이름", example = "홍길동")
        String name
) {
    public Member toEntity(String encodedPassword) {
        return Member.builder()
                .email(email)
                .password(encodedPassword)
                .name(name)
                .roles(List.of(Role.ROLE_USER))
                .build();
    }
}
