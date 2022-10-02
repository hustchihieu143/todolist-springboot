package com.example.demo.mapper;

import com.example.demo.dto.request.QuestionListPostDto;
import com.example.demo.dto.response.QuestionListGetResponse;
import com.example.demo.entity.QuestionList;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionListMapper {
    QuestionList questionListPostDtoToQuestionList(QuestionListPostDto postDto);
    QuestionListGetResponse questionListToQuestionListGetResponse(QuestionList questionList);
}
