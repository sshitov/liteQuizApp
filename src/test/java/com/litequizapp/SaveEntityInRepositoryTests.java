package com.litequizapp;

import com.litequizapp.entity.AnswerEntity;
import com.litequizapp.entity.CategoryEntity;
import com.litequizapp.entity.QuestionEntity;
import com.litequizapp.repository.AnswerRepository;
import com.litequizapp.repository.CategoryRepository;
import com.litequizapp.repository.QuestionRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SaveEntityInRepositoryTests {

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private QuestionRepository questionRepository;

  @Autowired
  private AnswerRepository answerRepository;

  @Test
  @DisplayName("Save category in the repository was a success")
  public void saveCategoryEntityInRepositoryTest() {
    CategoryEntity categoryEntity = categoryRepository
        .save(new CategoryEntity("Save Category Entity Test"));
    List<CategoryEntity> foundEntity = categoryRepository.findByTitle("Save Category Entity Test");
    assertEquals(categoryEntity.toString(), foundEntity.get(0).toString());
  }

  @Test
  @DisplayName("Save question in the repository was a success")
  public void saveQuestionEntityInRepositoryTest() {
    QuestionEntity questionEntity = questionRepository
        .save(new QuestionEntity("Save Question Entity Test"));
    List<QuestionEntity> foundEntity = questionRepository.findByTitle("Save Question Entity Test");
    assertEquals(questionEntity.toString(), foundEntity.get(0).toString());
  }

  @Test
  @DisplayName("Save answer in the repository was a success")
  public void saveAnswerEntityInRepositoryTest() {
    AnswerEntity answerEntity = answerRepository
        .save(new AnswerEntity("Save Answer Entity Test"));
    List<AnswerEntity> foundEntity = answerRepository.findByTitle("Save Answer Entity Test");
    assertEquals(answerEntity.toString(), foundEntity.get(0).toString());
  }

}