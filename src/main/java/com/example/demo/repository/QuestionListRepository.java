package com.example.demo.repository;

import com.example.demo.entity.QuestionList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface QuestionListRepository extends JpaRepository<QuestionList, Long> {
    List<QuestionList> findByName(String name);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);
}
