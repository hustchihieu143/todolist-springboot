package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
  private String code;
  private String message;

  private LocalDateTime timestamp;


  public ErrorResponse(String code, String message) {
    this.timestamp = LocalDateTime.now();
    this.code = code;
    this.message = message;
  }
}
