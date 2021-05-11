package com.litequizapp.repository;

import com.litequizapp.entity.AnswerEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<AnswerEntity, Long> {

  List<AnswerEntity> findByTitle(String title);

  Optional<AnswerEntity> findById(long id);

}
