package com.litequizapp;

import com.litequizapp.entity.AnswerEntity;
import com.litequizapp.entity.CategoryEntity;
import com.litequizapp.entity.QuestionEntity;
import com.litequizapp.repository.AnswerRepository;
import com.litequizapp.repository.CategoryRepository;
import com.litequizapp.repository.QuestionRepository;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class ApplicationTests {

  private static final Logger log = LoggerFactory.getLogger(ApplicationTests.class);

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private QuestionRepository questionRepository;

  @Autowired
  private AnswerRepository answerRepository;

  @Test
  public void saveAndGetCategoryEntityTest() {
    CategoryEntity categoryEntity = categoryRepository
        .save(new CategoryEntity("Save Category Entity Test"));

    List<CategoryEntity> foundEntity = categoryRepository.findByTitle("Save Category Entity Test");

    Assertions.assertEquals(categoryEntity.toString(), foundEntity.get(0).toString());
    log.info("saveAndGetCategoryEntityTest - 'Passed'");

  }

  @Test
  public void saveAndGetQuestionEntityTest() {
    QuestionEntity questionEntity = questionRepository
        .save(new QuestionEntity("Save Question Entity Test"));

    List<QuestionEntity> foundEntity = questionRepository.findByTitle("Save Question Entity Test");

    Assertions.assertEquals(questionEntity.toString(), foundEntity.get(0).toString());
    log.info("saveAndGetQuestionEntityTest - 'Passed'");

  }

  @Test
  public void saveAndGetAnswerEntityTest() {

    AnswerEntity answerEntity = answerRepository
        .save(new AnswerEntity("Save Answer Entity Test"));

    List<AnswerEntity> foundEntity = answerRepository.findByTitle("Save Answer Entity Test");

    Assertions.assertEquals(answerEntity.toString(), foundEntity.get(0).toString());
    log.info("saveAndGetAnswerEntityTest - 'Passed'");

  }

}