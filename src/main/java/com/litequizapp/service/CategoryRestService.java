package com.litequizapp.service;

import com.litequizapp.entity.CategoryEntity;
import com.litequizapp.exception.ElementNotFoundException;
import com.litequizapp.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
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
    if (category == null) {
      throw new ElementNotFoundException();
    }
    return category.toString();

  }

  public List<String> getAllCategory() {
    List<String> categories = new ArrayList<>();
    for (CategoryEntity category : categoryRepository.findAll()) {
      categories.add(category.toString());
    }
    return categories;

  }

  public void createCategory(String title) {
    categoryRepository.save(new CategoryEntity(title));

  }

  public void updateCategory(long id, String title) {
    CategoryEntity category = categoryRepository.findById(id);
    if (category == null) {
      throw new ElementNotFoundException();
    }
    category.setTitle(title);
    categoryRepository.save(category);
  }

  public void deleteCategory(long id) {
    CategoryEntity category = categoryRepository.findById(id);
    if (category == null) {
      throw new ElementNotFoundException();
    }
    categoryRepository.deleteById(id);

  }

}
