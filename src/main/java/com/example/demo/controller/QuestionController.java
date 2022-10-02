package com.example.demo.controller;

import com.example.demo.dto.request.QuestionPostDto;
import com.example.demo.dto.request.common.BulkDeleteDto;
import com.example.demo.dto.response.QuestionGetListResponse;
import com.example.demo.dto.response.QuestionGetResponse;
import com.example.demo.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
//@RequestMapping("/questions")
public class QuestionController {
    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

//    @GetMapping("/")
//    public ResponseEntity<QuestionGetListResponse> getQuestions() {
//        return ResponseEntity.ok(this.questionService.getQuestions());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionGetResponse> getQuestion(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.questionService.getQuestion(id));
    }

//    @PostMapping("/")
    @RequestMapping(value = "/questions", method = RequestMethod.POST)
    public ResponseEntity<?> createQuestion(@Valid @RequestBody QuestionPostDto questionPostDto) {
        this.questionService.createQuestion(questionPostDto);
        return ResponseEntity.status(201).body("Create question successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable("id") Long id, @Valid @RequestBody QuestionPostDto updateDto) {
        this.questionService.updateQuestion(id, updateDto);
        return ResponseEntity.ok("Update question successfully");
    }

    @PostMapping("/bulk-delete")
    public ResponseEntity<?> deleteQuestions(@Valid @RequestBody BulkDeleteDto dto) {
        this.questionService.deleteQuestions(dto.getIds());
        return ResponseEntity.ok("Bulk delete questions successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") Long id) {
        this.questionService.deleteQuestion(id);
        return ResponseEntity.ok("Delete question successfully");
    }
}
