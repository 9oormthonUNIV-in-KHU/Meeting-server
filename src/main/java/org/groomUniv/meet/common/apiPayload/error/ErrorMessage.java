package org.groomUniv.meet.common.apiPayload.error;

import lombok.Getter;

@Getter
public class ErrorMessage {

    private final String code;

    private final String message;

    public ErrorMessage(ErrorType errorType) {
        this.code = errorType.name();
        this.message = errorType.getMessage();
    }

    public ErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

}