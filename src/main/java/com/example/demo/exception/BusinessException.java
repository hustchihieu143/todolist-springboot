package com.example.demo.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException {

  private transient ErrorResponse errorResponse;

  public BusinessException(ErrorResponse errorResponse) {
    this.errorResponse = errorResponse;
  }
}
