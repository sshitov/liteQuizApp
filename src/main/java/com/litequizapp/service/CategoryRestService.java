package com.litequizapp.service;

import com.litequizapp.dto.CategoryDTO;
import com.litequizapp.entity.CategoryEntity;
import com.litequizapp.exception.ElementNotFoundException;
import com.litequizapp.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryRestService {

  private final CategoryRepository categoryRepository;

  public CategoryEntity getCategoryById(long id) {
    return categoryRepository.findById(id)
        .orElseThrow(() -> new ElementNotFoundException("Category with id: "+id+" - Not Found"));
  }

  public List<CategoryEntity> getAllCategories() {
    List<CategoryEntity> categories = new ArrayList<>();
    for (CategoryEntity category : categoryRepository.findAll()) {
      categories.add(category);
    }
    return categories;
  }

  public void createCategory(CategoryDTO categoryDTO) {
    CategoryEntity categoryEntity = new CategoryEntity(categoryDTO.getTitle());
    categoryRepository.save(categoryEntity);
  }

  public void updateCategory(long id, CategoryDTO categoryDTO) {
    CategoryEntity category = categoryRepository.findById(id)
        .orElseThrow(() -> new ElementNotFoundException("Category with id: "+id+" - Not Found"));
    category.setTitle(categoryDTO.getTitle());
    categoryRepository.save(category);
  }

  public void deleteCategory(long id) {
    categoryRepository.findById(id)
        .orElseThrow(() -> new ElementNotFoundException("Category with id: "+id+" - Not Found"));
    categoryRepository.deleteById(id);
  }

}
