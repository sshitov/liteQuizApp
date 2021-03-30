package com.litequizapp.service;

import com.litequizapp.dto.AnswerDTO;
import com.litequizapp.entity.AnswerEntity;
import com.litequizapp.entity.QuestionEntity;
import com.litequizapp.exception.AnswerBadRequestException;
import com.litequizapp.exception.ElementNotFoundException;
import com.litequizapp.repository.AnswerRepository;
import com.litequizapp.repository.QuestionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AnswerRestService {

  private final AnswerRepository answerRepository;
  private final QuestionRepository questionRepository;

  @Autowired
  public AnswerRestService(AnswerRepository answerRepository, QuestionRepository questionRepository) {
    this.answerRepository = answerRepository;
    this.questionRepository = questionRepository;
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


  public AnswerDTO createAnswer(AnswerDTO answerDTO) {
    if (answerDTO.getTitle() == null) {
      throw new AnswerBadRequestException();
    }
    try {
      QuestionEntity questionId = questionRepository.findById(answerDTO.getQuestionId()).get();
      AnswerEntity answerEntity = new AnswerEntity(answerDTO.getTitle(), answerDTO.getIsRight(), questionId);

      answerRepository.save(answerEntity);

    } catch (NoSuchElementException e) {
      throw new ElementNotFoundException();
    }

    return answerDTO;
  }


  public AnswerDTO updateAnswer(long id, AnswerDTO answerDTO) {
    AnswerEntity answer = answerRepository.findById(id);
    if (answer == null) {
      throw new ElementNotFoundException();
    }
    if (answerDTO.getTitle() == null) {
      throw new AnswerBadRequestException();
    }
    try {
      QuestionEntity questionId = questionRepository.findById(answerDTO.getQuestionId()).get();

      answer.setTitle(answerDTO.getTitle());
      answer.setIsRight(answerDTO.getIsRight());
      answer.setQuestion(questionId);
      answerRepository.save(answer);

    } catch (NoSuchElementException e) {
      throw new ElementNotFoundException();
    }

    return answerDTO;
  }

  public void deleteAnswer(long id) {
    AnswerEntity answer = answerRepository.findById(id);
    if (answer == null) {
      throw new ElementNotFoundException();
    }
    answerRepository.deleteById(id);

  }

}
