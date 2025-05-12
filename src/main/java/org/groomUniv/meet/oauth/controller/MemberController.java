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
            return ApiResponse.error(GlobalErrorType.E500);
        }
    }

    @PostMapping("/signup")
    public ApiResponse<?> signUp(@RequestBody SignUpRequest signUpRequest){
        SignUpResponse signUpResponse = memberService.signUp(signUpRequest);
        return ApiResponse.success(signUpResponse);

    }
}
