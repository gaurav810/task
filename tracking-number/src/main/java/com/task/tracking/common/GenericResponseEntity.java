package com.task.tracking.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class GenericResponseEntity extends ResponseEntity<GenericResponseBody> {

    public GenericResponseEntity(GenericResponseBody body, MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers, status);
    }

    public static GenericResponseEntity response(String message, Object data) {

        return new GenericResponseEntity(new GenericResponseBody(HttpStatus.OK.value(), true, message, data), null, HttpStatus.OK);
    }
}