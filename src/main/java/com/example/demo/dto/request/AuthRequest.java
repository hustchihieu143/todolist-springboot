package com.example.demo.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AuthRequest {
  @NotNull()
  @JsonProperty("email")
  private String email;
  @NotNull
  private String password;
}
