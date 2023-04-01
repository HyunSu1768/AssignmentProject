package com.signinproeject.domain.user.service.jwt;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TokenResponse {
    private String accessToken;

    private LocalDateTime expiredAt;
}
