package org.groomUniv.meet.oauth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record LoginRequest (
        @NotBlank       // 해당 값이 null이 아니고, 공백(""과 " " 모두 포함)이 아닌지 검증
        @Schema(description = "이메일", example = "abc1234@khu.ac.kr")
        String email,

        @NotBlank
        @Schema(description = "비밀번호", example = "1234")
        String password
){ }
