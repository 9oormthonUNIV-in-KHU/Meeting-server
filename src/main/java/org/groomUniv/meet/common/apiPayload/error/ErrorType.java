package org.groomUniv.meet.common.apiPayload.error;

import org.springframework.http.HttpStatus;

public interface ErrorType {

    String name();

    HttpStatus getStatus();

    String getMessage();
}
