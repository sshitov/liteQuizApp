package com.litequizapp.controller;

import com.litequizapp.dto.QuestionDTO;
import com.litequizapp.entity.QuestionEntity;
import com.litequizapp.exception.ElementNotFoundException;
import com.litequizapp.service.QuestionService;
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
@RequestMapping("/admin/question")
@RequiredArgsConstructor
public class QuestionController {

  private final QuestionService restService;

  @GetMapping
  public String getAllQuestions(Model model) {
    List<QuestionEntity> question = restService.getAllQuestions();
    model.addAttribute("question", question);
    return "question/question-list";
  }

  @GetMapping(value = "/{id}")
  public String getQuestionByIdRequest(Model model, @PathVariable String id) {
    long questionId = Long.parseLong(id);
    QuestionEntity question = null;
    try {
      question = restService.getQuestionById(questionId);
    } catch (ElementNotFoundException ex) {
      model.addAttribute("errorMessage", ex.getMessage());
    }
    model.addAttribute("question", question);
    return "question/question";
  }

  @PutMapping(value = "/create")
  public String createQuestionRequest(Model model, @Validated @ModelAttribute("question") QuestionDTO questionDTO,
      BindingResult result) {
    if (result.hasErrors()) {
      List<FieldError> errors = result.getFieldErrors();
      for (FieldError error : errors) {
        model.addAttribute("errorMessage", error.getDefaultMessage());
      }
      return "question/question-create";
    } else {
      restService.createQuestion(questionDTO);
      return "redirect:/admin/question";
    }
  }

  @GetMapping(value = "/create")
  public String showCreateQuestion(Model model) {
    QuestionDTO question = new QuestionDTO();
    model.addAttribute("question", question);
    return "question/question-create";
  }

  @PostMapping(value = "/{id}/update")
  public String updateQuestionRequest(Model model, @PathVariable String id,
      @Validated @ModelAttribute("question") QuestionDTO questionDTO, BindingResult result) {
    if (result.hasErrors()) {
      List<FieldError> errors = result.getFieldErrors();
      for (FieldError error : errors) {
        model.addAttribute("errorMessage", error.getDefaultMessage());
      }
      return "question/question-update";
    } else {
      long questionId = Long.parseLong(id);
      restService.updateQuestion(questionId, questionDTO);
      return "redirect:/admin/question";
    }
  }

  @GetMapping(value = "/{id}/update")
  public String showUpdateQuestion(Model model, @PathVariable String id) {
    long questionId = Long.parseLong(id);
    QuestionDTO question = null;
    try {
      QuestionEntity questionEntity = restService.getQuestionById(questionId);
      question = new QuestionDTO(questionEntity.getId(),questionEntity.getTitle(),questionId);
    } catch (ElementNotFoundException ex) {
      model.addAttribute("errorMessage", ex.getMessage());
    }
    model.addAttribute("question", question);
    return "question/question-update";
  }

  @DeleteMapping(value = "/{id}/delete")
  public String deleteQuestionByIdRequest(Model model, @PathVariable String id) {
    long questionId = Long.parseLong(id);
    try {
      restService.deleteQuestion(questionId);
      return "redirect:/admin/question";
    } catch (ElementNotFoundException ex) {
      model.addAttribute("errorMessage", ex.getMessage());
      return "question/question";
    }
  }

  @GetMapping(value = "/{id}/delete")
  public String showDeleteQuestionById(Model model, @PathVariable String id) {
    long questionId = Long.parseLong(id);
    QuestionEntity question = null;
    try {
      question = restService.getQuestionById(questionId);
    } catch (ElementNotFoundException ex) {
      model.addAttribute("errorMessage", ex.getMessage());
    }
    model.addAttribute("allowDelete", true);
    model.addAttribute("question", question);
    return "question/question";
  }

}
