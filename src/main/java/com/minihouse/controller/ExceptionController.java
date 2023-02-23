package com.minihouse.controller;

import com.minihouse.exception.BaseException;
import com.minihouse.exception.PostNotFoundException;
import com.minihouse.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<?> postNotFoundException(PostNotFoundException e) {
        return response(HttpStatus.NOT_FOUND, e);
    }

    private ResponseEntity<ErrorResponse> response(HttpStatus status, BaseException e) {
        return ResponseEntity.status(status)
            .body(ErrorResponse.builder()
                .code(e.getStatusCode())
                .message(e.getMessage())
                .build());
    }
}
