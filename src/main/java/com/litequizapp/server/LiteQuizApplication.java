package com.litequizapp.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LiteQuizApplication {

  @RequestMapping("/")
  public String home() {
    return "Hello 8080";
  }

  public static void main(String[] args) {
    SpringApplication.run(LiteQuizApplication.class, args);

  }

}
