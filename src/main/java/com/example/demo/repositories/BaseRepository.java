package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Person;

public interface BaseRepository extends JpaRepository<Person, Long> {
}
