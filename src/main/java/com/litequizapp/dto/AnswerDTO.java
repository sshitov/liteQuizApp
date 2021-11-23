package com.litequizapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {

  private Long id;

  @NotBlank
  private String title;

  @NotNull
  private Long questionId;

  private Boolean isRight = false;

}
