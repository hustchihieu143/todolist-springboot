package com.example.demo.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserRequest {

  @NotNull
  private String email;

  @NotNull
  private String name;

  @NotNull
  private String password;

  private int is_admin;
}
