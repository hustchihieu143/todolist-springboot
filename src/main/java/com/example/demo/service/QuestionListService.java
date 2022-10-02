package com.example.demo.service;

import com.example.demo.dto.request.QuestionListPostDto;
import com.example.demo.dto.response.QuestionListGetListResponse;
import com.example.demo.dto.response.QuestionListGetResponse;

public interface QuestionListService {
    public QuestionListGetListResponse getQuestionLists();

    public QuestionListGetResponse getQuestionList(Long id);

    public void createQuestionList(QuestionListPostDto questionListPostDto);

    public void updateQuestionList(Long id, QuestionListPostDto updateDto);

    public void deleteQuestionList(Long id);
}
