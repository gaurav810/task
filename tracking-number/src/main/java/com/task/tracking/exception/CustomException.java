package com.task.tracking.exception;

import com.task.tracking.constant.ErrorCode;
import lombok.Getter;

public class CustomException extends RuntimeException {

    @Getter
    ErrorCode errorCode;

    @Getter
    Object body;

    public CustomException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
