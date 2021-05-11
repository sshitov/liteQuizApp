package com.litequizapp.repository;

import com.litequizapp.entity.QuestionEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<QuestionEntity, Long> {

  List<QuestionEntity> findByTitle(String title);

  Optional<QuestionEntity> findById(long id);

}
