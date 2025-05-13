package org.groomUniv.meet.oauth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.groomUniv.meet.oauth.entity.Member;
import org.groomUniv.meet.oauth.enums.Role;

import java.util.List;

public record SignUpResponse(
        @Schema(description = "이메일", example = "abc1234@khu.ac.kr")
        String email,

        @Schema(description = "비밀번호", example = "1234")
        String password,

        @Schema(description = "이름", example = "홍길동")
        String name,

        @Schema(description = "Role", example = "[\"ROLE_USER\", \"ROLE_ADMIN\"]")
        List<Role> roles

) {
    public static SignUpResponse of(Member member) {
        return new SignUpResponse(
                member.getEmail(),
                member.getPassword(),
                member.getName(),
                member.getRoles()
        );
    }
}
