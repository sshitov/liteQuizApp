package com.litequizapp.controller;

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
  public ResponseEntity<List<String>> getAllQuestions() {
    return ResponseEntity.ok(restService.getAllQuestions());
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<String> getQuestionByIdRequest(@PathVariable String id) {
    long questionId = Long.parseLong(id);
    return ResponseEntity.ok(restService.getQuestionById(questionId));
  }

  @PutMapping
  public void createQuestionRequest(@RequestBody String title) {
    restService.createQuestion(title);

  }

  @PostMapping(value = "/{id}")
  public void updateQuestionRequest(@PathVariable String id, @RequestBody String title) {
    long questionId = Long.parseLong(id);
    restService.updateQuestion(questionId, title);

  }

  @DeleteMapping(value = "/{id}")
  public void deleteQuestionByIdRequest(@PathVariable String id) {
    long questionId = Long.parseLong(id);
    restService.deleteQuestion(questionId);

  }

}
