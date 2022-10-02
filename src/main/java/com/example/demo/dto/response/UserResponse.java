package com.example.demo.dto.response;

import lombok.Data;

@Data
public class UserResponse {
  private Long id;
  private String email;
  private String name;
  private int is_admin;
}
