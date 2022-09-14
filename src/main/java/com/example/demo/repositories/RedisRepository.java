package com.example.demo.repositories;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

@Component
public class RedisRepository {

  @Autowired
  private RedisTemplate template;

  public <T> void set(String key, T value) {
    template.opsForValue().set(key, value, 60, TimeUnit.SECONDS);
  }

  public <T> T get(String key) {
    return (T) template.opsForValue().get(key);
  }

  // opsForHash(): Tương ứng với cấu trúc Hash trong Redis. Value là một Object có
  // cấu trúc
  // opsForList(): Tương ứng với cấu trúc List trong Redis. Value là một list.
  // opsForSet(): Tương ứng với cấu trúc Set trong Redis.
  // opsForZSet(): Tương ứng với cấu trúc ZSet trong Redis.

}
