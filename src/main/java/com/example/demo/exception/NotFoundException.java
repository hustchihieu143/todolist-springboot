package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApplicationException {
    public NotFoundException(ErrorCode errorCode) {
        super(HttpStatus.NOT_FOUND, errorCode);
    }

    public NotFoundException(String code, String message) {
        super(HttpStatus.NOT_FOUND, code, message);
    }
}
