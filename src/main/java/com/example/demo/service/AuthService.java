package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.dto.request.AuthRequest;
import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.AuthResponse;

public interface AuthService {
  User createUser(UserRequest userRequest);

  AuthResponse login(AuthRequest loginRequest);
}
