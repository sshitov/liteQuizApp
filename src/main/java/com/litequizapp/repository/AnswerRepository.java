package com.litequizapp.repository;

import com.litequizapp.entity.AnswerEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<AnswerEntity, Long> {

  List<AnswerEntity> findByTitle(String title);

  AnswerEntity findById(long id);

}
