package com.example.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AnswerDto {
    @JsonProperty("content")
    @NotNull()
    private String content;

    @JsonProperty("isCorrect")
    @NotNull()
    private int isCorrect;
}
