package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class InternalServerException extends ApplicationException {
    public InternalServerException(ErrorCode errorCode) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, errorCode);
    }

    public InternalServerException(String code, String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_SERVER.getCode(), ErrorCode.INTERNAL_SERVER.getMessage());
    }
}
