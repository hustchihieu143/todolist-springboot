package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserEntity;
import com.example.demo.request.UserRequest;
import com.example.demo.response.BaseResponse;
import com.example.demo.service.AuthService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/register")
  public BaseResponse<UserEntity> register(@RequestBody UserRequest userRequest) {
    UserEntity user = authService.createUser(userRequest);
    return BaseResponse.ofSuccess(user);
  }
}
