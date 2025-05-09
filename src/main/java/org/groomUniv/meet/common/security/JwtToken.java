package org.groomUniv.meet.common.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public record JwtToken (
    String grantType,
    String accessToken,
    String refreshToken
){ }
