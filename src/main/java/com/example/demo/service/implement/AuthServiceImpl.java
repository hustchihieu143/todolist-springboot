package com.example.demo.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.exception.BusinessCode;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorResponse;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repositories.UserRepository;
import com.example.demo.request.UserRequest;
import com.example.demo.response.UserResponse;
import com.example.demo.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor

public class AuthServiceImpl implements AuthService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;

  @Override
  public UserEntity createUser(UserRequest userRequest) {
    UserEntity user = new UserEntity();
    user = userRepository.findByEmail(userRequest.getEmail());
    if (user != null) {
      throw new BusinessException(BusinessCode.ALREADY_EXISTS_USER);
    }
    user = userMapper.to(userRequest);

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String hashedPassword = passwordEncoder.encode(userRequest.getPassword());

    user.setPassword(hashedPassword);
    return userRepository.save(user);
  }

  @Override
  public UserResponse login() {
    // TODO Auto-generated method stub
    return null;
  }

}
