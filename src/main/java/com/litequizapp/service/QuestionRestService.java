package com.litequizapp.service;

import com.litequizapp.dto.QuestionDTO;
import com.litequizapp.entity.CategoryEntity;
import com.litequizapp.entity.QuestionEntity;
import com.litequizapp.exception.ElementNotFoundException;
import com.litequizapp.repository.CategoryRepository;
import com.litequizapp.repository.QuestionRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionRestService {

  private final QuestionRepository questionRepository;
  private final CategoryRepository categoryRepository;

  public QuestionEntity getQuestionById(long id) {
    return questionRepository.findById(id)
        .orElseThrow(() -> new ElementNotFoundException("Question with id: " + id + " - Not Found"));
  }

  public List<QuestionEntity> getAllQuestions() {
    List<QuestionEntity> questions = new ArrayList<>();
    for (QuestionEntity question : questionRepository.findAll()) {
      questions.add(question);
    }
    return questions;
  }

  public QuestionDTO createQuestion(QuestionDTO questionDto) {
    CategoryEntity categoryId = categoryRepository.findById(questionDto.getCategoryId())
        .orElseThrow(() -> new ElementNotFoundException("Category with id: " + questionDto.getCategoryId() + " - Not Found"));
    QuestionEntity questionEntity = new QuestionEntity(questionDto.getTitle(), categoryId);
    questionRepository.save(questionEntity);
    return questionDto;
  }

  public QuestionDTO updateQuestion(long id, QuestionDTO questionDto) {
    QuestionEntity question = questionRepository.findById(id)
        .orElseThrow(() -> new ElementNotFoundException("Question with id: " + id + " - Not Found"));
    CategoryEntity categoryId = categoryRepository.findById(questionDto.getCategoryId())
        .orElseThrow(() -> new ElementNotFoundException("Category with id: " + questionDto.getCategoryId() + " - Not Found"));
    question.setTitle(questionDto.getTitle());
    question.setCategory(categoryId);
    questionRepository.save(question);
    return questionDto;
  }

  public void deleteQuestion(long id) {
    questionRepository.findById(id)
        .orElseThrow(() -> new ElementNotFoundException("Question with id: " + id + " - Not Found"));
    questionRepository.deleteById(id);
  }

}
