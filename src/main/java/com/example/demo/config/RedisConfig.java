package com.example.demo.config;

import java.time.Duration;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
public class RedisConfig {
  @Value("${redis.host}")
  private String redisHost;

  @Value("${redis.port}")
  private int redisPort;

  @Value("${redis.expire_time}")
  private Long expireTime;

  @Bean
  public LettuceConnectionFactory redisConnectionFactory() {
    // Tạo Standalone Connection tới Redis
    return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisHost, redisPort));
  }

  @Bean
  @Primary
  public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    // tạo ra một RedisTemplate
    // Với Key là Object
    // Value là Object
    // RedisTemplate giúp chúng ta thao tác với Redis
    RedisTemplate redisTemplate = new RedisTemplate();
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    return redisTemplate;
  }

  @Bean
  public RedisCacheConfiguration cacheConfiguration() {
    RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
        .entryTtl(Duration.ofSeconds(expireTime))
        .disableCachingNullValues()
        .serializeValuesWith(
            RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    cacheConfig.usePrefix();
    return cacheConfig;
  }

  @Bean
  public RedisCacheManager cacheManager() {
    RedisCacheManager rcm = RedisCacheManager
        .builder(this.redisConnectionFactory())
        .cacheDefaults(this.cacheConfiguration())
        .build();
    return rcm;
  }
}
