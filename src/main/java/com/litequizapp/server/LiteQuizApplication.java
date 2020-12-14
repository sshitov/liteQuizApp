package com.litequizapp.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LiteQuizApplication {

  private static final Logger log = LoggerFactory.getLogger(LiteQuizApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(LiteQuizApplication.class, args);
  }

  @Bean
  public CommandLineRunner demo(CategoryRepository repository) {
    return (args) -> {
      // save a few categories
      repository.save(new CategoryEntity("Testing"));
      repository.save(new CategoryEntity("Development"));
      repository.save(new CategoryEntity("Business Analysis"));

      log.info("--------------------------------------------");
      // fetch all categories
      log.info("Categories found with findAll():");
      for (CategoryEntity category : repository.findAll()) {
        log.info(category.toString());
      }
      log.info("--------------------------------------------");

      // fetch an individual category by ID
      CategoryEntity category = repository.findById(2L);
      log.info("Categories found with findById(2L):");
      log.info(category.toString());
      log.info("--------------------------------------------");

      // fetch categories by title
      log.info("Category found with findByTitle('Testing'):");
      repository.findByTitle("Testing").forEach(testing -> {
        log.info(testing.toString());
      });
      log.info("--------------------------------------------");
    };
  }

}
