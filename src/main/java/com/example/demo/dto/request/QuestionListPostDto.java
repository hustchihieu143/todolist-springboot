package com.example.demo.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class QuestionListPostDto {
    @NotNull()
    private String code;

    @NotNull()
    private String name;

    private Long[] questionIds;
}
