package com.example.demo.service.implement;

import com.example.demo.common.Difficulty;
import com.example.demo.common.QuestionType;
import com.example.demo.dto.request.QuestionPostDto;
import com.example.demo.dto.response.QuestionGetListResponse;
import com.example.demo.dto.response.QuestionGetResponse;
import com.example.demo.entity.Answer;
import com.example.demo.entity.Question;
import com.example.demo.entity.QuestionList;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.repository.QuestionListRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    private QuestionRepository questionRepository;

    private QuestionListRepository questionListRepository;

    @Autowired
    private QuestionMapper questionMapper;

    public QuestionServiceImpl(QuestionRepository questionRepository, QuestionListRepository questionListRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionListRepository = questionListRepository;
        this.questionMapper = questionMapper;
    }

    @Override
    public QuestionGetListResponse getQuestions() {
        return null;
    }

    @Override
    public QuestionGetResponse getQuestion(Long id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question == null) {
            throw new NotFoundException(ErrorCode.QUESTION_NOT_FOUND);
        }
        return questionMapper.questionToQuestionGetResponse(question);
    }

    @Override
    public void createQuestion(QuestionPostDto questionPostDto) {
        System.out.println(questionPostDto);

        Long[] questionListIds = questionPostDto.getQuestionLists();
        List<QuestionList> questionLists = new ArrayList<>();
        if (questionListIds != null && questionListIds.length > 0) {
            questionLists = this.questionListRepository.findAllById(Arrays.asList(questionListIds));
        }


        Question question = this.questionMapper.questionPostDtoToQuestion(questionPostDto);
        if (question.getAnswers() != null) {
            for (Answer ans : question.getAnswers()) {
                ans.setQuestion(question);
            }
        }

        Question createdQuestion = this.questionRepository.save(question);

        if (questionLists.size() > 0) {
            createdQuestion.setQuestionLists(questionLists);
            this.questionRepository.save(createdQuestion);
        }
    }

    @Override
    public void updateQuestion(Long id, QuestionPostDto questionPostDto) {

    }

    @Override
    public void deleteQuestion(Long id) {
        this.questionRepository.deleteById(id);
    }

    @Override
    public void deleteQuestions(Long[] ids) {
        this.questionRepository.deleteAllById(Arrays.asList(ids));
    }
}
