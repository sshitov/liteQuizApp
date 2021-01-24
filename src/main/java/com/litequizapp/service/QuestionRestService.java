package com.litequizapp.service;

import com.litequizapp.entity.QuestionEntity;
import com.litequizapp.exception.ElementNotFoundException;
import com.litequizapp.repository.QuestionRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class QuestionRestService {

  private final QuestionRepository questionRepository;

  @Autowired
  public QuestionRestService(QuestionRepository questionRepository) {
    this.questionRepository = questionRepository;
  }

  public String getQuestionById(long id) {
    QuestionEntity question = questionRepository.findById(id);
    if (question == null) {
      throw new ElementNotFoundException();
    }
    return question.toString();

  }

  public List<String> getAllQuestions() {
    List<String> questions = new ArrayList<>();
    for (QuestionEntity question : questionRepository.findAll()) {
      questions.add(question.toString());
    }
    return questions;

  }

  public void createQuestion(String title) {
    questionRepository.save(new QuestionEntity(title));

  }

  public void updateQuestion(long id, String title) {
    QuestionEntity question = questionRepository.findById(id);
    if (question == null) {
      throw new ElementNotFoundException();
    }
    question.setTitle(title);
    questionRepository.save(question);
  }

  public void deleteQuestion(long id) {
    QuestionEntity question = questionRepository.findById(id);
    if (question == null) {
      throw new ElementNotFoundException();
    }
    questionRepository.deleteById(id);

  }

}
