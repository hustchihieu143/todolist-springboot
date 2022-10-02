package com.example.demo.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter

public class ApplicationException extends RuntimeException{

  private String code;
  private String message;
  private HttpStatus status;

  public ApplicationException(HttpStatus status, ErrorCode errorCode) {
    this(status, errorCode.getCode(), errorCode.getMessage());
  }

  public ApplicationException(HttpStatus status, String code, String message) {
    super();
    this.status = status;
    this.code = code;
    this.message = message;
  }
}
