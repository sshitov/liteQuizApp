package com.litequizapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AnswerDTO {

  @NotBlank
  private String title;

  @NotNull
  private Long questionId;

  private Boolean isRight;

}
