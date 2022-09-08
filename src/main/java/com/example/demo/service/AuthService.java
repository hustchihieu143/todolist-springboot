package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.request.AuthRequest;
import com.example.demo.request.UserRequest;
import com.example.demo.response.AuthResponse;

public interface AuthService {
  User createUser(UserRequest userRequest);

  AuthResponse login(AuthRequest loginRequest);
}
