package org.groomUniv.meet.oauth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.groomUniv.meet.oauth.entity.Member;

public record SignUpResponse(
        @Schema(description = "이메일", example = "abc1234@khu.ac.kr")
        String email,

        @Schema(description = "비밀번호", example = "1234")
        String password

        //나머지 추가 예정
) {
    public static SignUpResponse of(Member member) {
        return new SignUpResponse(
                member.getEmail(),
                member.getPassword()
        );
    }
}
