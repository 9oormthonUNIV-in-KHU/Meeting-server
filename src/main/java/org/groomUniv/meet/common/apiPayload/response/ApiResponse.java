package org.groomUniv.meet.common.apiPayload.response;

import org.groomUniv.meet.common.apiPayload.error.ErrorMessage;
import org.groomUniv.meet.common.apiPayload.error.ErrorType;

public record ApiResponse<T>(ResultType result, T data, ErrorMessage error) {

    public static ApiResponse<?> success() {
        return new ApiResponse<>(ResultType.SUCCESS, null, null);
    }

    public static <S> ApiResponse<S> success(S data) {
        return new ApiResponse<>(ResultType.SUCCESS, data, null);
    }

    public static ApiResponse<?> error(ErrorType error) {
        return new ApiResponse<>(ResultType.ERROR, null, new ErrorMessage(error));
    }

}