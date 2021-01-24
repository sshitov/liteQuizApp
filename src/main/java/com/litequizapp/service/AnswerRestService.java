package com.litequizapp.service;

import com.litequizapp.entity.AnswerEntity;
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

  public String getAnswerById(long id) {
    AnswerEntity answer = answerRepository.findById(id);
    if (answer == null) {
      throw new ElementNotFoundException();
    }
    return answer.toString();

  }

  public List<String> getAllAnswers() {
    List<String> answers = new ArrayList<>();
    for (AnswerEntity answer : answerRepository.findAll()) {
      answers.add(answer.toString());
    }
    return answers;

  }

  public void createAnswer(String title) {
    answerRepository.save(new AnswerEntity(title));

  }

  public void updateAnswer(long id, String title) {
    AnswerEntity answer = answerRepository.findById(id);
    if (answer == null) {
      throw new ElementNotFoundException();
    }
    answer.setTitle(title);
    answerRepository.save(answer);
  }

  public void deleteAnswer(long id) {
    AnswerEntity answer = answerRepository.findById(id);
    if (answer == null) {
      throw new ElementNotFoundException();
    }
    answerRepository.deleteById(id);

  }

}
