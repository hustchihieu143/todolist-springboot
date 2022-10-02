package com.example.demo.repository;

import com.example.demo.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Person;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long>, JpaSpecificationExecutor {
}
