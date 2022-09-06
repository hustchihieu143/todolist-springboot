package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.UserEntity;

@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  UserEntity findByEmail(String email);
}
