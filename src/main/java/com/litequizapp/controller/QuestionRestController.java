package com.litequizapp.controller;

import com.litequizapp.entity.QuestionEntity;
import com.litequizapp.service.QuestionRestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class QuestionRestController {

  private final QuestionRestService restService;

  @Autowired
  public QuestionRestController(QuestionRestService restService) {
    this.restService = restService;
  }

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
  public QuestionEntity createCategoryRequest(@RequestBody QuestionEntity title) {
    return restService.createQuestion(title);
  }

  @PostMapping(value = "/{id}")
  public QuestionEntity updateCategoryRequest(@PathVariable String id, @RequestBody QuestionEntity title) {
    long questionId = Long.parseLong(id);
    return restService.updateQuestion(questionId, title);

  }

  @DeleteMapping(value = "/{id}")
  public void deleteQuestionByIdRequest(@PathVariable String id) {
    long questionId = Long.parseLong(id);
    restService.deleteQuestion(questionId);

  }

}
