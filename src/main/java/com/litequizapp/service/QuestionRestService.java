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

  public QuestionEntity getQuestionById(long id) {
    QuestionEntity question = questionRepository.findById(id);
    if (question == null) {
      throw new ElementNotFoundException();
    }
    return question;

  }

  public List<QuestionEntity> getAllQuestions() {
    List<QuestionEntity> questions = new ArrayList<>();
    for (QuestionEntity question : questionRepository.findAll()) {
      questions.add(question);
    }
    return questions;

  }

  public QuestionEntity createQuestion(QuestionEntity title) {
    return questionRepository.save(title);

  }

  public QuestionEntity updateQuestion(long id, QuestionEntity title) {
    QuestionEntity question = questionRepository.findById(id);
    if (question == null) {
      throw new ElementNotFoundException();
    }
    question.setTitle(title.getTitle());
    return questionRepository.save(question);
  }

  public void deleteQuestion(long id) {
    QuestionEntity question = questionRepository.findById(id);
    if (question == null) {
      throw new ElementNotFoundException();
    }
    questionRepository.deleteById(id);

  }

}
