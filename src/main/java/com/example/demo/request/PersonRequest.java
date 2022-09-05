package com.example.demo.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PersonRequest {
  @NotNull
  private String name;
  @NotNull
  private int age;
}
