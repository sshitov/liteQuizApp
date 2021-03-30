package com.litequizapp.dto;

import lombok.Data;

@Data
public class AnswerDTO {

  private String title;
  private Long questionId;
  private Boolean isRight;

}
