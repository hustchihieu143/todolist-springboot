package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
  USER_ALREADY_EXISTS("USER_ALREADY_EXISTS", "User already existed"),
  QUESTION_NOT_FOUND("QUESTION_NOT_FOUND", "Question not found"),
  DUPLICATE_NAME("DUPLICATE_NAME", "Entity with the same name already existed"),
  INTERNAL_SERVER("INTERNAL_SERVER_ERROR", "Error occured in server");
  private final String code;
  private final String message;

  private ErrorCode(String code, String message) {
    this.code = code;
    this.message = message;
  }
  public String getCode() {
    return this.code;
  }

  public String getMessage() {
    return this.message;
  }
}