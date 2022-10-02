package com.example.demo.dto.response;

import com.example.demo.dto.request.AnswerDto;
import lombok.Data;

import java.util.List;

@Data
public class QuestionGetResponse {
    private String type;

    private String questionText;

    private String difficulty;

    private String explanation;

    private List<AnswerDto> answers;
}
