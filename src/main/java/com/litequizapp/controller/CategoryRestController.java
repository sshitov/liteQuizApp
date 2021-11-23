package com.litequizapp.controller;

import com.litequizapp.dto.CategoryDTO;
import com.litequizapp.entity.CategoryEntity;
import com.litequizapp.exception.ElementNotFoundException;
import com.litequizapp.service.CategoryRestService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class CategoryRestController {

  private final CategoryRestService restService;

  @GetMapping
  public String getAllCategories(Model model) {
    List<CategoryEntity> category = restService.getAllCategories();
    model.addAttribute("category", category);
    return "category/category-list";
  }

  @GetMapping(value = "/{id}")
  public String getCategoryByIdRequest(Model model, @PathVariable String id) {
    long categoryId = Long.parseLong(id);
    CategoryEntity category = null;
    try {
      category = restService.getCategoryById(categoryId);
    } catch (ElementNotFoundException ex) {
      model.addAttribute("errorMessage", ex.getMessage());
    }
    model.addAttribute("category", category);
    return "category/category";
  }

  @PutMapping(value = "/create")
  public String createCategoryRequest(Model model, @Validated @ModelAttribute("category") CategoryDTO categoryDTO,
      BindingResult result) {
    if (result.hasErrors()) {
      List<FieldError> errors = result.getFieldErrors();
      for (FieldError error : errors) {
        model.addAttribute("errorMessage", error.getDefaultMessage());
      }
      return "category/category-create";
    } else {
      restService.createCategory(categoryDTO);
      return "redirect:/admin/category";
    }
  }

  @GetMapping(value = "/create")
  public String showCreateCategory(Model model) {
    CategoryEntity category = new CategoryEntity();
    model.addAttribute("category", category);
    return "category/category-create";
  }

  @PostMapping(value = "/{id}/update")
  public String updateCategoryRequest(Model model, @PathVariable String id,
      @Validated @ModelAttribute("category") CategoryDTO categoryDTO, BindingResult result) {
    if (result.hasErrors()) {
      List<FieldError> errors = result.getFieldErrors();
      for (FieldError error : errors) {
        model.addAttribute("errorMessage", error.getDefaultMessage());
      }
      return "category/category-update";
    } else {
      long categoryId = Long.parseLong(id);
      restService.updateCategory(categoryId, categoryDTO);
      return "redirect:/admin/category";
    }
  }

  @GetMapping(value = "/{id}/update")
  public String showUpdateCategory(Model model, @PathVariable String id) {
    long categoryId = Long.parseLong(id);
    CategoryEntity category = null;
    try {
      category = restService.getCategoryById(categoryId);
    } catch (ElementNotFoundException ex) {
      model.addAttribute("errorMessage", ex.getMessage());
    }
    model.addAttribute("category", category);
    return "category/category-update";
  }

  @DeleteMapping(value = "/{id}/delete")
  public String deleteCategoryByIdRequest(Model model, @PathVariable String id) {
    long categoryId = Long.parseLong(id);
    try {
      restService.deleteCategory(categoryId);
      return "redirect:/admin/category";
    } catch (ElementNotFoundException ex) {
      model.addAttribute("errorMessage", ex.getMessage());
      return "category/category";
    }
  }

  @GetMapping(value = "/{id}/delete")
  public String showDeleteCategoryById(Model model, @PathVariable String id) {
    long categoryId = Long.parseLong(id);
    CategoryEntity category = null;
    try {
      category = restService.getCategoryById(categoryId);
    } catch (ElementNotFoundException ex) {
      model.addAttribute("errorMessage", ex.getMessage());
    }
    model.addAttribute("allowDelete", true);
    model.addAttribute("category", category);
    return "category/category";
  }

}
