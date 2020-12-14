package com.litequizapp.server;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

  List<CategoryEntity> findByTitle(String title);

  CategoryEntity findById(long id);

}
