package com.litequizapp.service;

import com.litequizapp.entity.QuestionEntity;
import com.litequizapp.exception.ElementNotFoundException;
import com.litequizapp.exception.QuestionBadRequestException;
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
  private CategoryRestService categoryRestService;

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

  public QuestionEntity createQuestion(QuestionEntity questionEntity) {
    if (questionEntity.getTitle() == null){
      throw new QuestionBadRequestException();
    }

    return questionRepository.save(questionEntity);

  }

  public QuestionEntity updateQuestion(long id, QuestionEntity questionEntity) {
    QuestionEntity question = questionRepository.findById(id);
    if (question == null) {
      throw new ElementNotFoundException();
    }
    if (questionEntity.getTitle() == null){
      throw new QuestionBadRequestException();
    }

    question.setTitle(questionEntity.getTitle());
    question.setCategoryId(questionEntity.getCategoryId());
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
