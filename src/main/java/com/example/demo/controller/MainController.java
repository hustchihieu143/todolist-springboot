package com.example.demo.controller;

import java.util.Collections;
import java.util.Map;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repositories.RedisRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@Data
@RequiredArgsConstructor
public class MainController {

  @Value("${redis.host}")
  private String appTitle;

  @GetMapping("/user")
  public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
    return Collections.singletonMap("name", principal.getAttribute("email"));
  }

}
