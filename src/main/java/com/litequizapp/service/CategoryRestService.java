package com.litequizapp.service;

import com.litequizapp.dto.CategoryDTO;
import com.litequizapp.entity.CategoryEntity;
import com.litequizapp.exception.CategoryBadRequestException;
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

  public CategoryDTO createCategory(CategoryDTO categoryDTO) {
    if (categoryDTO.getTitle() == null) {
      throw new CategoryBadRequestException();
    }

    CategoryEntity categoryEntity = new CategoryEntity(categoryDTO.getTitle());
    categoryRepository.save(categoryEntity);

    return categoryDTO;
  }

  public CategoryDTO updateCategory(long id, CategoryDTO categoryDTO) {
    CategoryEntity category = categoryRepository.findById(id);
    if (category == null) {
      throw new ElementNotFoundException();
    }
    if (categoryDTO.getTitle() == null) {
      throw new CategoryBadRequestException();
    }

    category.setTitle(categoryDTO.getTitle());
    categoryRepository.save(category);

    return categoryDTO;
  }

  public void deleteCategory(long id) {
    CategoryEntity category = categoryRepository.findById(id);
    if (category == null) {
      throw new ElementNotFoundException();
    }
    categoryRepository.deleteById(id);

  }

}
