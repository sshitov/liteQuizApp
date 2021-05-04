package com.litequizapp.controller;

import com.litequizapp.dto.AnswerDTO;
import com.litequizapp.entity.AnswerEntity;
import com.litequizapp.service.AnswerRestService;
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
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerRestController {

  private final AnswerRestService restService;

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
  public AnswerDTO createCategoryRequest(@Validated @RequestBody AnswerDTO answerDTO) {
    return restService.createAnswer(answerDTO);
  }

  @PostMapping(value = "/{id}")
  public AnswerDTO updateCategoryRequest(@PathVariable String id, @Validated @RequestBody AnswerDTO answerDTO) {
    long answerId = Long.parseLong(id);
    return restService.updateAnswer(answerId, answerDTO);
  }

  @DeleteMapping(value = "/{id}")
  public void deleteAnswerByIdRequest(@PathVariable String id) {
    long answerId = Long.parseLong(id);
    restService.deleteAnswer(answerId);
  }

}
