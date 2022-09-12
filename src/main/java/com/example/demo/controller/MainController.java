package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
  @RequestMapping(value = "/test", method = RequestMethod.GET)
  @ResponseBody
  public String hello() {
    return "Hello Spring Boot test";
  }

}
