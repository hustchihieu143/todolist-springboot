package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);
}
