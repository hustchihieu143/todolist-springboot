package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserEntity;
import com.example.demo.request.AuthRequest;
import com.example.demo.request.UserRequest;
import com.example.demo.response.BaseResponse;
import com.example.demo.response.AuthResponse;
import com.example.demo.security.services.UserDetailsImpl;
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

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest loginRequest) {
    return ResponseEntity.ok(authService.login(loginRequest));
  }

}
