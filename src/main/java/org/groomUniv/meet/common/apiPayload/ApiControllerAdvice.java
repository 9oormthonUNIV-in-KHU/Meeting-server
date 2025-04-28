package org.groomUniv.meet.common.apiPayload;

import lombok.extern.log4j.Log4j2;
import org.groomUniv.meet.common.apiPayload.error.CoreException;
import org.groomUniv.meet.common.apiPayload.error.GlobalErrorType;
import org.groomUniv.meet.common.apiPayload.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class ApiControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception e) {
        log.error("Exception : {}", e.getMessage(), e);
        return new ResponseEntity<>(ApiResponse.error(GlobalErrorType.E500), GlobalErrorType.E500.getStatus());
    }

    @ExceptionHandler(CoreException.class)
    public ResponseEntity<ApiResponse<?>> handleCoreException(CoreException e) {
        log.error("CoreException : {}", e.getMessage(), e);
        return new ResponseEntity<>(ApiResponse.error(e.getErrorType()), e.getErrorType().getStatus());
    }

}