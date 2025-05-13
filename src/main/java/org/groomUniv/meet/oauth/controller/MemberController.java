package org.groomUniv.meet.oauth.controller;

import lombok.RequiredArgsConstructor;
import org.groomUniv.meet.common.apiPayload.error.GlobalErrorType;
import org.groomUniv.meet.common.apiPayload.response.ApiResponse;
import org.groomUniv.meet.common.security.JwtToken;
import org.groomUniv.meet.oauth.dto.LoginRequest;
import org.groomUniv.meet.oauth.dto.SignUpRequest;
import org.groomUniv.meet.oauth.dto.SignUpResponse;
import org.groomUniv.meet.oauth.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login")
    public ApiResponse<?> login(@RequestBody LoginRequest loginRequest){
        try{
            JwtToken jwtToken = memberService.login(loginRequest.email(), loginRequest.password());
            return ApiResponse.success(jwtToken);
        }catch (Exception e){
            return ApiResponse.error("INVALID_EMAIL_OR_PASSWORD", "이메일 또는 비밀번호가 일치하지 않습니다.");
        }
    }

    @PostMapping("/signup")
    public ApiResponse<?> signUp(@RequestBody SignUpRequest signUpRequest){
        try{
            SignUpResponse signUpResponse = memberService.signUp(signUpRequest);
            return ApiResponse.success(signUpResponse);
        }catch (Exception e){
            return ApiResponse.error("DUPLICATE_EMAIL", "이미 존재하는 회원입니다");
        }
    }
}
