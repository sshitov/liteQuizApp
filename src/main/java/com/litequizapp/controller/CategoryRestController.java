package com.litequizapp.controller;

import com.litequizapp.service.CategoryRestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryRestController {

  private final CategoryRestService restService;

  @Autowired
  public CategoryRestController(CategoryRestService restService) {
    this.restService = restService;
  }

  @GetMapping
  public ResponseEntity<List<String>> getAllCategories() {
    return ResponseEntity.ok(restService.getAllCategory());
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<String> getCategoryByIdRequest(@PathVariable String id) {
    long categoryId = Long.parseLong(id);
    return ResponseEntity.ok(restService.getCategoryById(categoryId));
  }

  @PutMapping
  public void createCategoryRequest(@RequestBody String title) {
    restService.createCategory(title);

  }

  @PostMapping(value = "/{id}")
  public void updateCategoryRequest(@PathVariable String id, @RequestBody String title) {
    long categoryId = Long.parseLong(id);
    restService.updateCategory(categoryId, title);

  }

  @DeleteMapping(value = "/{id}")
  public void deleteCategoryByIdRequest(@PathVariable String id) {
    long categoryId = Long.parseLong(id);
    restService.deleteCategory(categoryId);

  }


}
