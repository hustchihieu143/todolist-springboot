package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class BusinessCode {
  private BusinessCode() {
  }

  public static final ErrorResponse ALREADY_EXISTS_USER = new ErrorResponse("ALREADY_EXISTS_USER",
      "ALREADY_EXISTS_USER",
      HttpStatus.BAD_REQUEST);

  public static final ErrorResponse SUCCESS = new ErrorResponse("SUCCESS-01", "SUCCESS", HttpStatus.OK);

  public static final ErrorResponse INTERNAL_SERVER = new ErrorResponse("INTERNAL-SERVER", "Something went wrong",
      HttpStatus.INTERNAL_SERVER_ERROR);

  public static final ErrorResponse NOT_FOUND_COMPANY_EMPLOYEE = new ErrorResponse("NOT_FOUND_COMPANY_EMPLOYEE",
      "Not found company employee with id", HttpStatus.NOT_FOUND);

  public static final ErrorResponse BUILDING_EMPLOYEE_EXISTED = new ErrorResponse("BUILDING_EMPLOYEE_EXISTED",
      "Building employee existed", HttpStatus.BAD_REQUEST);
}
