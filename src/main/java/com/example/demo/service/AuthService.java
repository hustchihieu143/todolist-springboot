package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.request.UserRequest;
import com.example.demo.response.UserResponse;

public interface AuthService {
  UserEntity createUser(UserRequest userRequest);

  UserResponse login();
}
