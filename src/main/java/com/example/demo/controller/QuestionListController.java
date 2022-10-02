package com.example.demo.controller;

import com.example.demo.dto.request.QuestionListPostDto;
import com.example.demo.dto.response.QuestionListGetListResponse;
import com.example.demo.dto.response.QuestionListGetResponse;
import com.example.demo.service.QuestionListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/question-lists")
public class QuestionListController {
    private QuestionListService questionListService;

    public QuestionListController(QuestionListService questionListService) {
        this.questionListService = questionListService;
    }

    @GetMapping("/")
    public ResponseEntity<QuestionListGetListResponse> getQuestionList() {
        return ResponseEntity.ok(this.questionListService.getQuestionLists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionListGetResponse> getQuestion(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.questionListService.getQuestionList(id));
    }

    @PostMapping("/")
    public ResponseEntity<?> createQuestionList(@Valid @RequestBody QuestionListPostDto questionListPostDto) {
        this.questionListService.createQuestionList(questionListPostDto);
        return ResponseEntity.status(201).body("Create question list successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuestionList(@PathVariable("id") Long id, @Valid @RequestBody QuestionListPostDto updateDto) {
        this.questionListService.updateQuestionList(id, updateDto);
        return ResponseEntity.ok("Update question list successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestionList(@PathVariable("id") Long id) {
        this.questionListService.deleteQuestionList(id);
        return ResponseEntity.ok("Delete question list successfully");
    }
}
