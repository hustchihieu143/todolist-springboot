package com.example.demo.mapper;

import com.example.demo.dto.request.QuestionPostDto;
import com.example.demo.dto.response.QuestionGetResponse;
import com.example.demo.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(
        componentModel = "spring"
)
public interface QuestionMapper {
    @Mapping(target = "questionLists", ignore = true)
    Question questionPostDtoToQuestion(QuestionPostDto questionPostDto);

    QuestionGetResponse questionToQuestionGetResponse(Optional<Question> question);
}
