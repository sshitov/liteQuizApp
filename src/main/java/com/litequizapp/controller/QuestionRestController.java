package com.litequizapp.controller;

import com.litequizapp.dto.QuestionDTO;
import com.litequizapp.entity.QuestionEntity;
import com.litequizapp.service.QuestionRestService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionRestController {

  private final QuestionRestService restService;

  @GetMapping
  public ResponseEntity<List<QuestionEntity>> getAllQuestions() {
    return ResponseEntity.ok(restService.getAllQuestions());
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<QuestionEntity> getQuestionByIdRequest(@PathVariable String id) {
    long questionId = Long.parseLong(id);
    return ResponseEntity.ok(restService.getQuestionById(questionId));
  }

  @PutMapping
  public QuestionDTO createCategoryRequest(@Validated @RequestBody QuestionDTO questionDto) {
    return restService.createQuestion(questionDto);
  }

  @PostMapping(value = "/{id}")
  public QuestionDTO updateCategoryRequest(@PathVariable String id, @Validated @RequestBody QuestionDTO questionDTO) {
    long questionId = Long.parseLong(id);
    return restService.updateQuestion(questionId, questionDTO);
  }

  @DeleteMapping(value = "/{id}")
  public void deleteQuestionByIdRequest(@PathVariable String id) {
    long questionId = Long.parseLong(id);
    restService.deleteQuestion(questionId);
  }

}
