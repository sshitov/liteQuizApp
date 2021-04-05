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
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswerRestService {

  private final AnswerRepository answerRepository;
  private final QuestionRepository questionRepository;


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
    if (answerDTO.getTitle() == null || answerDTO.getQuestionId() == null) {
      throw new AnswerBadRequestException();
    }

      QuestionEntity questionId = questionRepository.findById(answerDTO.getQuestionId()).orElseThrow(ElementNotFoundException::new);
      AnswerEntity answerEntity = new AnswerEntity(answerDTO.getTitle(), answerDTO.getIsRight(), questionId);

      answerRepository.save(answerEntity);

    return answerDTO;
  }


  public AnswerDTO updateAnswer(long id, AnswerDTO answerDTO) {
    AnswerEntity answer = answerRepository.findById(id);
    if (answer == null) {
      throw new ElementNotFoundException();
    }
    if (answerDTO.getTitle() == null || answerDTO.getQuestionId() == null) {
      throw new AnswerBadRequestException();
    }

      QuestionEntity questionId = questionRepository.findById(answerDTO.getQuestionId()).orElseThrow(ElementNotFoundException::new);

      answer.setTitle(answerDTO.getTitle());
      answer.setIsRight(answerDTO.getIsRight());
      answer.setQuestion(questionId);

      answerRepository.save(answer);

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
