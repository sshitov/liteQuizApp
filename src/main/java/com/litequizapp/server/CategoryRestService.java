package com.litequizapp.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class CategoryRestService {

  private final CategoryRepository categoryRepository;

  @Autowired
  public CategoryRestService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public String getCategoryById(long id) {
    CategoryEntity category = categoryRepository.findById(id);
    return category.toString();

  }

  public void createCategory(String title) {
    categoryRepository.save(new CategoryEntity(title));
  }

}
