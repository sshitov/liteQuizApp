package com.litequizapp.service;

import com.litequizapp.dto.QuestionDTO;
import com.litequizapp.entity.CategoryEntity;
import com.litequizapp.entity.QuestionEntity;
import com.litequizapp.exception.ElementNotFoundException;
import com.litequizapp.exception.QuestionBadRequestException;
import com.litequizapp.repository.CategoryRepository;
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
  private final CategoryRepository categoryRepository;

  @Autowired
  public QuestionRestService(QuestionRepository questionRepository, CategoryRepository categoryRepository) {
    this.questionRepository = questionRepository;
    this.categoryRepository = categoryRepository;
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

  public QuestionDTO createQuestion(QuestionDTO questionDto) {

    if (questionDto.getTitle() == null || questionDto.getCategoryId() == null) {
      throw new QuestionBadRequestException();
    }

      CategoryEntity categoryId = categoryRepository.findById(questionDto.getCategoryId()).orElseThrow(ElementNotFoundException::new);
      QuestionEntity questionEntity = new QuestionEntity(questionDto.getTitle(), categoryId);

      questionRepository.save(questionEntity);

    return questionDto;

  }

  public QuestionDTO updateQuestion(long id, QuestionDTO questionDto) {

    QuestionEntity question = questionRepository.findById(id);
    if (question == null) {
      throw new ElementNotFoundException();
    }
    if (questionDto.getTitle() == null || questionDto.getCategoryId() == null) {
      throw new QuestionBadRequestException();
    }

      CategoryEntity categoryId = categoryRepository.findById(questionDto.getCategoryId()).orElseThrow(ElementNotFoundException::new);

      question.setTitle(questionDto.getTitle());
      question.setCategory(categoryId);

      questionRepository.save(question);

    return questionDto;
  }

  public void deleteQuestion(long id) {
    QuestionEntity question = questionRepository.findById(id);
    if (question == null) {
      throw new ElementNotFoundException();
    }
    questionRepository.deleteById(id);

  }

}
