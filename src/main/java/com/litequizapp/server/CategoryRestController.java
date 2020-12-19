package com.litequizapp.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

  @GetMapping("/all")
  public ResponseEntity getAllCategories() {
    return ResponseEntity.ok(restService.getAllCategory());
  }

  @GetMapping("/id/{id}")
  public ResponseEntity getCategoryByIdRequest(@PathVariable String id) {
    long Id = Long.parseLong(id);
    return ResponseEntity.ok(restService.getCategoryById(Id));
  }

  @GetMapping("/title/{title}")
  public ResponseEntity getCategoryByTitleRequest(@PathVariable String title) {
    return ResponseEntity.ok(restService.getCategoryByTitle(title));

  }

  @PostMapping("/create")
  public void createCategoryRequest(@RequestBody String title) {
    restService.createCategory(title);

  }

  @PostMapping("/delete")
  public void deleteCategoryByIdRequest(@RequestBody String id) {
    long Id = Long.parseLong(id);
    restService.deleteCategory(Id);

  }

}
