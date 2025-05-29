package org.groomUniv.meet.oauth.service;

import lombok.RequiredArgsConstructor;
import org.groomUniv.meet.common.security.JwtToken;
import org.groomUniv.meet.common.security.JwtTokenProvider;
import org.groomUniv.meet.oauth.dto.SignUpRequest;
import org.groomUniv.meet.oauth.dto.SignUpResponse;
import org.groomUniv.meet.oauth.entity.Member;
import org.groomUniv.meet.oauth.repository.MemberRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public JwtToken login(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        return jwtTokenProvider.generateToken(authentication);
    }

    @Transactional
    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {

        if (memberRepository.existsByEmail(signUpRequest.email())){
            throw new IllegalArgumentException("이미 사용중인 이메일 입니다.");
        }

        String encodedPassword = passwordEncoder.encode(signUpRequest.password());
        Member member = memberRepository.save(signUpRequest.toMember(encodedPassword));
        return SignUpResponse.of(member);

    }
}
