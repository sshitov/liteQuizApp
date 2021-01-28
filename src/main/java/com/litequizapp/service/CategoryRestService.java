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


  public CategoryEntity getCategoryById(long id) {
    CategoryEntity category = categoryRepository.findById(id);
    if (category == null) {
      throw new ElementNotFoundException();
    }
    return category;

  }

  public List<CategoryEntity> getAllCategories() {
    List<CategoryEntity> categories = new ArrayList<>();
    for (CategoryEntity category : categoryRepository.findAll()) {
      categories.add(category);
    }
    return categories;

  }

  public CategoryEntity createCategory(CategoryEntity title) {
    return categoryRepository.save(title);

  }

  public CategoryEntity updateCategory(long id, CategoryEntity title) {
    CategoryEntity category = categoryRepository.findById(id);
    if (category == null) {
      throw new ElementNotFoundException();
    }
    category.setTitle(title.getTitle());
    return categoryRepository.save(category);
  }

  public void deleteCategory(long id) {
    CategoryEntity category = categoryRepository.findById(id);
    if (category == null) {
      throw new ElementNotFoundException();
    }
    categoryRepository.deleteById(id);

  }

}
