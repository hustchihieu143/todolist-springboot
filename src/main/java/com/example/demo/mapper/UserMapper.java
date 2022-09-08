package com.example.demo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.example.demo.entity.User;
import com.example.demo.request.UserRequest;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserMapper {
  public User to(UserRequest userRequest) {
    User user = new User();
    BeanUtils.copyProperties(userRequest, user);
    return user;
  }
}
