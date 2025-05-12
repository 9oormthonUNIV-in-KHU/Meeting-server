//package org.groomUniv.meet.oauth.dto;
//
//import io.swagger.v3.oas.annotations.media.Schema;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.groomUniv.meet.oauth.entity.Member;
//import org.groomUniv.meet.oauth.enums.Role;
//
//import java.util.List;
//
//@Getter
//@Setter
//@NoArgsConstructor
//public class SignUpRequest {
//
//    @Schema(description = "이메일", example = "abc1234@khu.ac.kr")
//    private String email;
//
//    @Schema(description = "비밀번호", example = "1234")
//    private String password;
//
//    public SignUpRequest(String email, String password) {
//        this.email = email;
//        this.password = password;
//    }
//
//    public Member toEntity(String encodedPassword) {
//        return Member.builder()
//                .email(email)
//                .password(encodedPassword)
//                .roles(List.of(Role.ROLE_USER))
//                .build();
//    }
//}

package org.groomUniv.meet.oauth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.groomUniv.meet.oauth.entity.Member;
import org.groomUniv.meet.oauth.enums.Role;

import java.util.List;

public record SignUpRequest(
        @Schema(description = "이메일", example = "abc1234@khu.ac.kr")
        String email,

        @Schema(description = "비밀번호", example = "1234")
        String password

        //나머지 추가 예정
) {
    public Member toEntity(String encodedPassword) {
        return Member.builder()
                .email(email)
                .password(encodedPassword)
                .roles(List.of(Role.ROLE_USER))
                .build();
    }
}
