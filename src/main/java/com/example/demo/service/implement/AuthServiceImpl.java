package com.example.demo.service.implement;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.exception.BusinessCode;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repositories.RedisRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.request.AuthRequest;
import com.example.demo.request.UserRequest;
import com.example.demo.response.AuthResponse;
import com.example.demo.response.RefreshTokenResponse;
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
  private final String Prefix_Refresh_Token = "auth_";
  private final RedisRepository redisRepository;

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
      System.out.println("authenticate: " + authenticate);
      String accessToken = jwtTokenUtil.generateAccessToken((UserDetails) authenticate.getPrincipal());
      String refreshToken = jwtTokenUtil.generateRefreshToken((UserDetails) authenticate.getPrincipal());
      UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
      User user = userRepository.findByEmail(userDetails.getUsername());
      redisRepository.set(Prefix_Refresh_Token + user.getEmail(), refreshToken);
      return new AuthResponse(user.getId(), user.getEmail(), accessToken, refreshToken);
    } catch (Exception ex) {
      ex.printStackTrace();
      throw ex;
    }
  }

  @Override
  public RefreshTokenResponse RefreshToken(String token) {

    String email = jwtTokenUtil.getEmail(token);
    String tokenStored = redisRepository.get(Prefix_Refresh_Token + email);
    System.out.println("tokenStored: " + token);
    System.out.println("tokenStored: " + tokenStored);
    System.out.println(tokenStored != token);
    if (!tokenStored.equals(token) || tokenStored == null) {
      System.out.println("not pass");
      throw new BusinessException(BusinessCode.REFRESH_TOKEN_NOT_FOUND);
    }
    System.out.println("pass");
    String accessToken = jwtTokenUtil.generateTokenFromEmail(email);
    return new RefreshTokenResponse(accessToken, token);
  }

}
