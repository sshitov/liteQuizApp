package com.litequizapp.repository;

import com.litequizapp.entity.QuestionEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<QuestionEntity, Long> {

  List<QuestionEntity> findByTitle(String title);

  QuestionEntity findById(long id);

}
