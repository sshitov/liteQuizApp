package com.litequizapp;

import com.litequizapp.dto.AnswerDTO;
import com.litequizapp.dto.CategoryDTO;
import com.litequizapp.dto.QuestionDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DTOValidationTests {

  private Validator validator;
  private CategoryDTO category;
  private QuestionDTO question;
  private AnswerDTO answer;
  private List<String> validationErrors;

  @BeforeEach
  public void setUp() {
    category = new CategoryDTO();
    question = new QuestionDTO();
    answer = new AnswerDTO();
    validationErrors = new ArrayList<>();
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("CategoryDTO with the filled title doesn't return exceptions")
  public void categoryDTOIsValid() {
    Set<ConstraintViolation<CategoryDTO>> violations = validator
        .validate(createCategoryDTO("Test valid category title"));
    assertTrue(violations.isEmpty(), "should be empty, but violations size: " + violations.size());
  }

  @Test
  @DisplayName("QuestionDTO with the filled title and category id doesn't return exceptions")
  public void questionDTOIsValid() {
    Set<ConstraintViolation<QuestionDTO>> violations = validator
        .validate(createQuestionDTO("Test valid question title", 1L));
    assertTrue(violations.isEmpty(), "should be empty, but violations size: " + violations.size());
  }

  @Test
  @DisplayName("AnswerDTO with the filled title and question id doesn't return exceptions")
  public void answerDTOIsValid() {
    Set<ConstraintViolation<AnswerDTO>> violations = validator
        .validate(createAnswerDTO("Test valid answer title", 1L, true));
    assertTrue(violations.isEmpty(), "should be empty, but violations size: " + violations.size());
  }

  @Test
  @DisplayName("CategoryDTO with empty title returned exception and validation message is correct")
  public void categoryTitleIsEmpty() {
    Set<ConstraintViolation<CategoryDTO>> violations = validator.validate(createCategoryDTO(null));
    assertEquals(1, violations.size(), "should be 1 exception");

    violations.forEach(e -> validationErrors.add(e.getMessage()));
    assertTrue(validationErrors.contains("The title can't be empty"), "Error message isn't correct");
  }

  @Test
  @DisplayName("QuestionDTO with empty title returned exception and validation message is correct")
  public void questionTitleIsEmpty() {
    Set<ConstraintViolation<QuestionDTO>> violations = validator.validate(createQuestionDTO(null, 1L));
    assertEquals(1, violations.size(), "should be 1 exception");

    violations.forEach(e -> validationErrors.add(e.getMessage()));
    assertTrue(validationErrors.contains("The title can't be empty"), "Error message isn't correct");
  }

  @Test
  @DisplayName("QuestionDTO with empty category id returned exception and validation message is correct")
  public void categoryIdInQuestionIsEmpty() {
    Set<ConstraintViolation<QuestionDTO>> violations = validator
        .validate(createQuestionDTO("Valid question title", null));
    assertEquals(1, violations.size(), "should be 1 exception");

    violations.forEach(e -> validationErrors.add(e.getMessage()));
    assertTrue(validationErrors.contains("related entry id can't be empty"), "Error message isn't correct");
  }

  @Test
  @DisplayName("AnswerDTO with empty title returned exception and validation message is correct")
  public void answerTitleIsEmpty() {
    Set<ConstraintViolation<AnswerDTO>> violations = validator.validate(createAnswerDTO(null, 1L, false));
    assertEquals(1, violations.size(), "should be 1 exception");

    violations.forEach(e -> validationErrors.add(e.getMessage()));
    assertTrue(validationErrors.contains("The title can't be empty"), "Error message isn't correct");
  }

  @Test
  @DisplayName("AnswerDTO with empty question id returned exception and validation message is correct")
  public void questionIdInAnswerIsEmpty() {
    Set<ConstraintViolation<AnswerDTO>> violations = validator
        .validate(createAnswerDTO("Valid answer title", null, true));
    assertEquals(1, violations.size(), "should be 1 exception");

    violations.forEach(e -> validationErrors.add(e.getMessage()));
    assertTrue(validationErrors.contains("related entry id can't be empty"), "Error message isn't correct");
  }

  public CategoryDTO createCategoryDTO(String title) {
    category.setTitle(title);
    return category;
  }

  public QuestionDTO createQuestionDTO(String title, Long categoryId) {
    question.setTitle(title);
    question.setCategoryId(categoryId);
    return question;
  }

  public AnswerDTO createAnswerDTO(String title, Long questionId, boolean isRight) {
    answer.setTitle(title);
    answer.setQuestionId(questionId);
    answer.setIsRight(isRight);
    return answer;
  }

}
