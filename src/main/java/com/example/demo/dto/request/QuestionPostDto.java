package com.example.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class QuestionPostDto {
    @JsonProperty("type")
    @NotNull()
    private String type;

    @JsonProperty("questionText")
    @NotNull()
    private String questionText;

    @JsonProperty("difficulty")
    @NotNull()
    private String difficulty;

    @JsonProperty("explanation")
    @NotNull()
    private String explanation;

    @JsonProperty("answers")
    @NotNull()
    @Valid()
    private List<AnswerDto> answers;

    @JsonProperty("questionLists")
    private Long[] questionLists;
}
