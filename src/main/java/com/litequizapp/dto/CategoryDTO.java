package com.litequizapp.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDTO {

  @NotBlank
  private String title;

}
