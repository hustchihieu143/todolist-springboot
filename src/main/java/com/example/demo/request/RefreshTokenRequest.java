package com.example.demo.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RefreshTokenRequest {
  @NotBlank
  String token;
}
