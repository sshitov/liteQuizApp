package com.litequizapp.controller;

import com.litequizapp.entity.AnswerEntity;
import com.litequizapp.service.AnswerRestService;
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
@RequestMapping("/answer")
public class AnswerRestController {

  private final AnswerRestService restService;

  @Autowired
  public AnswerRestController(AnswerRestService restService) {
    this.restService = restService;
  }

  @GetMapping
  public ResponseEntity<List<AnswerEntity>> getAllAnswers() {
    return ResponseEntity.ok(restService.getAllAnswers());
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<AnswerEntity> getAnswerByIdRequest(@PathVariable String id) {
    long answerId = Long.parseLong(id);
    return ResponseEntity.ok(restService.getAnswerById(answerId));
  }

  @PutMapping
  public AnswerEntity createCategoryRequest(@RequestBody AnswerEntity title) {
    return restService.createAnswer(title);
  }

  @PostMapping(value = "/{id}")
  public AnswerEntity updateCategoryRequest(@PathVariable String id, @RequestBody AnswerEntity title) {
    long answerId = Long.parseLong(id);
    return restService.updateAnswer(answerId, title);

  }

  @DeleteMapping(value = "/{id}")
  public void deleteAnswerByIdRequest(@PathVariable String id) {
    long answerId = Long.parseLong(id);
    restService.deleteAnswer(answerId);

  }

}
