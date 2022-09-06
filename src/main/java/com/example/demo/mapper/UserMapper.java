package com.example.demo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.example.demo.entity.UserEntity;
import com.example.demo.request.UserRequest;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserMapper {
  public UserEntity to(UserRequest userRequest) {
    UserEntity user = new UserEntity();
    BeanUtils.copyProperties(userRequest, user);
    return user;
  }
}
