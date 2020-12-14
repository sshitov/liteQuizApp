package com.litequizapp.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/category")
public class CategoryRestController {

  private final CategoryRestService restService;

  @Autowired
  public CategoryRestController(CategoryRestService restService) {
    this.restService = restService;
  }

  @GetMapping()
  public ResponseEntity getCategoryByIdRequest(@RequestParam(value = "id") String id) {
    long Id = Long.parseLong(id);
    return ResponseEntity.ok(restService.getCategoryById(Id));
  }

  @PostMapping(path = "/create")
  public void createCategoryRequest(@RequestBody String title) {
    restService.createCategory(title);

  }

}
