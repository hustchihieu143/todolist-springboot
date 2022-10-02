package com.example.demo.service;

import com.example.demo.dto.request.QuestionPostDto;
import com.example.demo.dto.response.QuestionGetResponse;
import com.example.demo.dto.response.QuestionGetListResponse;
import com.example.demo.entity.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    public QuestionGetListResponse getQuestions();

    public QuestionGetResponse getQuestion(Long id);

    public void createQuestion(QuestionPostDto postDto);

    public void updateQuestion(Long id, QuestionPostDto updateDto);

    public void deleteQuestion(Long id);

    public void deleteQuestions(Long[] ids);
}
