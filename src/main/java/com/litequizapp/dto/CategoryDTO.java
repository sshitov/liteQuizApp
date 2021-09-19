package com.litequizapp.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDTO {

  private Long id;

  @NotBlank
  private String title;

}
