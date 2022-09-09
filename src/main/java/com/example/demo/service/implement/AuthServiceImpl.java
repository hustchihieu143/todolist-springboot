package com.example.demo.service.implement;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.exception.BusinessCode;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repositories.UserRepository;
import com.example.demo.request.AuthRequest;
import com.example.demo.request.UserRequest;
import com.example.demo.response.AuthResponse;
import com.example.demo.security.jwt.JwtTokenUtil;
import com.example.demo.service.AuthService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;
  private final AuthenticationManager authenticationManager;
  private final JwtTokenUtil jwtTokenUtil;
  private final Logger log;

  @Override
  public User createUser(UserRequest userRequest) {
    User user = new User();
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
  public AuthResponse login(AuthRequest request) {
    // TODO Auto-generated method stub
    try {

      Authentication authenticate = authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
      log.info("authenticated: ", authenticate.toString());
      String accessToken = jwtTokenUtil.generateAccessToken((UserDetails) authenticate.getPrincipal());

      UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
      User user = userRepository.findByEmail(userDetails.getUsername());

      return new AuthResponse(user.getEmail(), accessToken, null);
    } catch (Exception ex) {
      ex.printStackTrace();
      throw ex;
    }
  }

}
