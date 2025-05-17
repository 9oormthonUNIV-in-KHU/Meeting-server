package org.groomUniv.meet.common.apiPayload.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GlobalErrorType implements ErrorType {

    E500(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 내부 오류입니다."),
    MEMBER_NOT_FOUND(HttpStatus.UNAUTHORIZED, "Member Not Found."), // "존재하지 않는 사용자" 401 Unauthorized
    ;

    private final HttpStatus status;

    private final String message;
}
