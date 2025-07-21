package com.task.tracking.constant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    BAD_REQUEST(HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR);

    @Getter
    private final HttpStatus httpStatus;
}
