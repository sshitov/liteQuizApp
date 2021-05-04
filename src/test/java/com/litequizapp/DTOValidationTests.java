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

  @BeforeEach
  public void setUp() {

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("CategoryDTO with the filled title doesn't return exceptions")
  public void categoryDTOIsValid() {
    CategoryDTO dto = new CategoryDTO();
    dto.setTitle("Test valid category title");
    Set<ConstraintViolation<CategoryDTO>> violations = validator.validate(dto);
    assertTrue(violations.isEmpty(), "should be empty, but violations size: " + violations.size());
  }

  @Test
  @DisplayName("QuestionDTO with the filled title and category id doesn't return exceptions")
  public void questionDTOIsValid() {
    QuestionDTO dto = new QuestionDTO();
    dto.setTitle("Test valid question title");
    dto.setCategoryId(1L);
    Set<ConstraintViolation<QuestionDTO>> violations = validator.validate(dto);
    assertTrue(violations.isEmpty(), "should be empty, but violations size: " + violations.size());
  }

  @Test
  @DisplayName("AnswerDTO with the filled title and question id doesn't return exceptions")
  public void answerDTOIsValid() {
    AnswerDTO dto = new AnswerDTO();
    dto.setTitle("Test valid answer title");
    dto.setQuestionId(1L);
    dto.setIsRight(true);
    Set<ConstraintViolation<AnswerDTO>> violations = validator.validate(dto);
    assertTrue(violations.isEmpty(), "should be empty, but violations size: " + violations.size());
  }

  @Test
  @DisplayName("CategoryDTO with empty title returned exception and validation message is correct")
  public void categoryTitleIsEmpty() {
    CategoryDTO dto = new CategoryDTO();
    dto.setTitle(null);
    Set<ConstraintViolation<CategoryDTO>> violations = validator.validate(dto);
    assertEquals(1, violations.size(), "should be 1 exception");

    List<String> validationErrors = new ArrayList<>();
    violations.forEach(e -> validationErrors.add(e.getMessage()));
    assertTrue(validationErrors.contains("The title can't be empty"), "Error message isn't correct");
  }

  @Test
  @DisplayName("QuestionDTO with empty title returned exception and validation message is correct")
  public void questionTitleIsEmpty() {
    QuestionDTO dto = new QuestionDTO();
    dto.setTitle(null);
    dto.setCategoryId(1L);
    Set<ConstraintViolation<QuestionDTO>> violations = validator.validate(dto);
    assertEquals(1, violations.size(), "should be 1 exception");

    List<String> validationErrors = new ArrayList<>();
    violations.forEach(e -> validationErrors.add(e.getMessage()));
    assertTrue(validationErrors.contains("The title can't be empty"), "Error message isn't correct");
  }

  @Test
  @DisplayName("QuestionDTO with empty category id returned exception and validation message is correct")
  public void categoryIdInQuestionIsEmpty() {
    QuestionDTO dto = new QuestionDTO();
    dto.setTitle("Valid question title");
    dto.setCategoryId(null);
    Set<ConstraintViolation<QuestionDTO>> violations = validator.validate(dto);
    assertEquals(1, violations.size(), "should be 1 exception");

    List<String> validationErrors = new ArrayList<>();
    violations.forEach(e -> validationErrors.add(e.getMessage()));
    assertTrue(validationErrors.contains("related entry id can't be empty"), "Error message isn't correct");
  }

  @Test
  @DisplayName("AnswerDTO with empty title returned exception and validation message is correct")
  public void answerTitleIsEmpty() {
    AnswerDTO dto = new AnswerDTO();
    dto.setTitle(null);
    dto.setQuestionId(1L);
    dto.setIsRight(false);
    Set<ConstraintViolation<AnswerDTO>> violations = validator.validate(dto);
    assertEquals(1, violations.size(), "should be 1 exception");

    List<String> validationErrors = new ArrayList<>();
    violations.forEach(e -> validationErrors.add(e.getMessage()));
    assertTrue(validationErrors.contains("The title can't be empty"), "Error message isn't correct");
  }

  @Test
  @DisplayName("AnswerDTO with empty question id returned exception and validation message is correct")
  public void questionIdInAnswerIsEmpty() {
    AnswerDTO dto = new AnswerDTO();
    dto.setTitle("Valid answer title");
    dto.setQuestionId(null);
    Set<ConstraintViolation<AnswerDTO>> violations = validator.validate(dto);
    assertEquals(1, violations.size(), "should be 1 exception");

    List<String> validationErrors = new ArrayList<>();
    violations.forEach(e -> validationErrors.add(e.getMessage()));
    assertTrue(validationErrors.contains("related entry id can't be empty"), "Error message isn't correct");
  }

}
