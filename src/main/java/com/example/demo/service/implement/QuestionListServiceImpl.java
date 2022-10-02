package com.example.demo.service.implement;

import com.example.demo.dto.request.QuestionListPostDto;
import com.example.demo.dto.response.QuestionListGetListResponse;
import com.example.demo.dto.response.QuestionListGetResponse;
import com.example.demo.entity.QuestionList;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.QuestionListMapper;
import com.example.demo.repository.QuestionListRepository;
import com.example.demo.service.QuestionListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class QuestionListServiceImpl implements QuestionListService {
    private QuestionListRepository questionListRepository;

    private QuestionListMapper questionListMapper;

    public QuestionListServiceImpl(QuestionListRepository questionListRepository, QuestionListMapper questionListMapper) {
        this.questionListRepository = questionListRepository;
        this.questionListMapper = questionListMapper;
    }

    @Override
    public QuestionListGetListResponse getQuestionLists() {
        return null;
    }

    @Override
    public QuestionListGetResponse getQuestionList(Long id) {
        final QuestionList questionList = this.questionListRepository.findById(id).orElseThrow();

        return this.questionListMapper.questionListToQuestionListGetResponse(questionList);

    }

    @Override
    @Transactional
    public void createQuestionList(QuestionListPostDto questionListPostDto) {
        boolean isExistDuplicateNameQuestionList = this.questionListRepository.existsByName(questionListPostDto.getName());
        if (isExistDuplicateNameQuestionList) {
            throw new BadRequestException(ErrorCode.DUPLICATE_NAME);
        }

        this.questionListRepository.save(this.questionListMapper.questionListPostDtoToQuestionList(questionListPostDto));
    }

    @Override
    public void updateQuestionList(Long id, QuestionListPostDto updateDto) {
        if (updateDto.getName() != null && this.questionListRepository.existsByNameAndIdNot(updateDto.getName(), id)) {
            throw new BadRequestException(ErrorCode.DUPLICATE_NAME);
        }

        QuestionList questionList = this.questionListRepository.findById(id)
                .orElseThrow();

        this.questionListRepository.save(questionList);
    }

    @Override
    public void deleteQuestionList(Long id) {
        QuestionList questionList = this.questionListRepository.findById(id)
                .orElseThrow();
        this.questionListRepository.delete(questionList);
    }
}
