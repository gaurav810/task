package com.task.tracking.exception;

import com.task.tracking.common.GenericResponseBody;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex, @NonNull HttpHeaders headers,
                                                                @NonNull HttpStatusCode status, @NonNull WebRequest request) {

        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        GenericResponseBody response = new GenericResponseBody(HttpStatus.BAD_REQUEST.value(), false, errorMessage, null);
        log.info("Error Response: " + response.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException ex) {

        GenericResponseBody response = new GenericResponseBody(ex.getErrorCode().getHttpStatus().value(), false, ex.getMessage(), ex.getBody());
        log.info("Error Response: " + response.toString());
        return new ResponseEntity<>(response, ex.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {

        log.error("Unhandled exception caught: ", ex);
        GenericResponseBody response = new GenericResponseBody(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,
                "An unexpected error occurred. Please try again later.", null);
        log.info("Error Response: " + response.toString());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
