package com.litequizapp.service;

import com.litequizapp.entity.AnswerEntity;
import com.litequizapp.exception.AnswerBadRequestException;
import com.litequizapp.exception.ElementNotFoundException;
import com.litequizapp.repository.AnswerRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AnswerRestService {

  private final AnswerRepository answerRepository;

  @Autowired
  public AnswerRestService(AnswerRepository answerRepository) {
    this.answerRepository = answerRepository;
  }

  public AnswerEntity getAnswerById(long id) {
    AnswerEntity answer = answerRepository.findById(id);
    if (answer == null) {
      throw new ElementNotFoundException();
    }
    return answer;

  }

  public List<AnswerEntity> getAllAnswers() {
    List<AnswerEntity> answers = new ArrayList<>();
    for (AnswerEntity answer : answerRepository.findAll()) {
      answers.add(answer);
    }
    return answers;

  }

  public AnswerEntity createAnswer(AnswerEntity answerEntity) {
    if (answerEntity.getTitle() == null){
      throw new AnswerBadRequestException();
    }

    return answerRepository.save(answerEntity);

  }

  public AnswerEntity updateAnswer(long id, AnswerEntity answerEntity) {
    AnswerEntity answer = answerRepository.findById(id);
    if (answer == null) {
      throw new ElementNotFoundException();
    }
    if (answerEntity.getTitle() == null){
      throw new AnswerBadRequestException();
    }

    answer.setTitle(answerEntity.getTitle());
    answer.setIsRight(answerEntity.getIsRight());
    answer.setQuestionId(answerEntity.getQuestionId());
    return answerRepository.save(answer);

  }

  public void deleteAnswer(long id) {
    AnswerEntity answer = answerRepository.findById(id);
    if (answer == null) {
      throw new ElementNotFoundException();
    }
    answerRepository.deleteById(id);

  }

}
