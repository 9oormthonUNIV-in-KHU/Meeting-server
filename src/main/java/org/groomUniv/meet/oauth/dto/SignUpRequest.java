package org.groomUniv.meet.oauth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record SignUpRequest(
        @Schema(description = "이메일", example = "abc1234@khu.ac.kr")
        String email,

        @Schema(description = "비밀번호", example = "1234")
        String password
) {
}
