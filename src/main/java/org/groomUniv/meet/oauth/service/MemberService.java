package org.groomUniv.meet.oauth.service;

import org.groomUniv.meet.common.security.JwtToken;

public interface MemberService {

    public JwtToken login(String email, String password);
}
