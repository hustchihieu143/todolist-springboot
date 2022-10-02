package com.example.demo.mapper;

import com.example.demo.dto.request.AnswerDto;
import com.example.demo.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring"
)
public interface AnswerMapper {
    Answer answerDtoToAnswer(AnswerDto answerDto);

    AnswerDto answerToAnswerDto(Answer answer);
}
