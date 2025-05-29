package org.groomUniv.meet.oauth.service;

import org.groomUniv.meet.common.security.JwtToken;
import org.groomUniv.meet.oauth.dto.SignUpRequest;
import org.groomUniv.meet.oauth.dto.SignUpResponse;

public interface MemberService {

    public JwtToken login(String email, String password);

    public SignUpResponse signUp(SignUpRequest signUpRequest);
}
