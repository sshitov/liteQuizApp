package com.litequizapp;

import com.litequizapp.entity.CategoryEntity;
import com.litequizapp.repository.CategoryRepository;
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

  @Test
  public void saveAndGetCategoryEntityTest() {
    CategoryEntity categoryEntity = categoryRepository
        .save(new CategoryEntity("Save Category Entity Test"));

    List<CategoryEntity> foundEntity = categoryRepository.findByTitle("Save Category Entity Test");

    Assertions.assertEquals(categoryEntity.toString(), foundEntity.get(0).toString());
    log.info("saveAndGetCategoryEntityTest - 'Passed'");

  }

}