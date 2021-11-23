package com.litequizapp.controller;

import com.litequizapp.dto.AnswerDTO;
import com.litequizapp.entity.AnswerEntity;
import com.litequizapp.exception.ElementNotFoundException;
import com.litequizapp.service.AnswerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/answer")
@RequiredArgsConstructor
public class AnswerController {

  private final AnswerService restService;

  @GetMapping
  public String getAllAnswers(Model model) {
    List<AnswerEntity> answer = restService.getAllAnswers();
    model.addAttribute("answer", answer);
    return "answer/answer-list";
  }

  @GetMapping(value = "/{id}")
  public String getAnswerByIdRequest(Model model, @PathVariable String id) {
    long answerId = Long.parseLong(id);
    AnswerEntity answer = null;
    try {
      answer = restService.getAnswerById(answerId);
    } catch (ElementNotFoundException ex) {
      model.addAttribute("errorMessage", ex.getMessage());
    }
    model.addAttribute("answer", answer);
    return "answer/answer";
  }

  @PutMapping(value = "/create")
  public String createAnswerRequest(Model model, @Validated @ModelAttribute("answer") AnswerDTO answerDTO,
      BindingResult result) {
    if (result.hasErrors()) {
      List<FieldError> errors = result.getFieldErrors();
      for (FieldError error : errors) {
        model.addAttribute("errorMessage", error.getDefaultMessage());
      }
      return "answer/answer-create";
    } else {
      restService.createAnswer(answerDTO);
      return "redirect:/admin/answer";
    }
  }

  @GetMapping(value = "/create")
  public String showCreateAnswer(Model model) {
    AnswerDTO answer = new AnswerDTO();
    model.addAttribute("answer", answer);
    return "answer/answer-create";
  }

  @PostMapping(value = "/{id}/update")
  public String updateAnswerRequest(Model model, @PathVariable String id,
      @Validated @ModelAttribute("answer") AnswerDTO answerDTO, BindingResult result) {
    if (result.hasErrors()) {
      List<FieldError> errors = result.getFieldErrors();
      for (FieldError error : errors) {
        model.addAttribute("errorMessage", error.getDefaultMessage());
      }
      return "answer/answer-update";
    } else {
      long answerId = Long.parseLong(id);
      restService.updateAnswer(answerId, answerDTO);
      return "redirect:/admin/answer";
    }
  }

  @GetMapping(value = "/{id}/update")
  public String showUpdateAnswer(Model model, @PathVariable String id) {
    long answerId = Long.parseLong(id);
    AnswerDTO answer = null;
    try {
      AnswerEntity answerEntity = restService.getAnswerById(answerId);
      answer = new AnswerDTO(answerEntity.getId(),answerEntity.getTitle(),answerId,answerEntity.getIsRight());
    } catch (ElementNotFoundException ex) {
      model.addAttribute("errorMessage", ex.getMessage());
    }
    model.addAttribute("answer", answer);
    return "answer/answer-update";
  }

  @DeleteMapping(value = "/{id}/delete")
  public String deleteAnswerByIdRequest(Model model, @PathVariable String id) {
    long answerId = Long.parseLong(id);
    try {
      restService.deleteAnswer(answerId);
      return "redirect:/admin/answer";
    } catch (ElementNotFoundException ex) {
      model.addAttribute("errorMessage", ex.getMessage());
      return "answer/answer";
    }
  }

  @GetMapping(value = "/{id}/delete")
  public String showDeleteAnswerById(Model model, @PathVariable String id) {
    long answerId = Long.parseLong(id);
    AnswerEntity answer = null;
    try {
      answer = restService.getAnswerById(answerId);
    } catch (ElementNotFoundException ex) {
      model.addAttribute("errorMessage", ex.getMessage());
    }
    model.addAttribute("allowDelete", true);
    model.addAttribute("answer", answer);
    return "answer/answer";
  }

}
