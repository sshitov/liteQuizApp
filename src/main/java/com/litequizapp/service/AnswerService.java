package com.litequizapp.service;

import com.litequizapp.dto.AnswerDTO;
import com.litequizapp.entity.AnswerEntity;
import com.litequizapp.entity.QuestionEntity;
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
public class AnswerService {

  private final AnswerRepository answerRepository;
  private final QuestionRepository questionRepository;

  public AnswerEntity getAnswerById(long id) {
    return answerRepository.findById(id)
        .orElseThrow(() -> new ElementNotFoundException("Answer with id: " + id + " - Not Found"));
  }

  public List<AnswerEntity> getAllAnswers() {
    List<AnswerEntity> answers = new ArrayList<>();
    for (AnswerEntity answer : answerRepository.findAll()) {
      answers.add(answer);
    }
    return answers;
  }

  public void createAnswer(AnswerDTO answerDTO) {
    QuestionEntity questionId = questionRepository.findById(answerDTO.getQuestionId())
        .orElseThrow(
            () -> new ElementNotFoundException("Question with id: " + answerDTO.getQuestionId() + " - Not Found"));
    AnswerEntity answerEntity = new AnswerEntity(answerDTO.getTitle(), answerDTO.getIsRight(), questionId);
    answerRepository.save(answerEntity);
  }

  public void updateAnswer(long id, AnswerDTO answerDTO) {
    AnswerEntity answer = answerRepository.findById(id)
        .orElseThrow(() -> new ElementNotFoundException("Answer with id: " + id + " - Not Found"));
    QuestionEntity questionId = questionRepository.findById(answerDTO.getQuestionId())
        .orElseThrow(
            () -> new ElementNotFoundException("Question with id: " + answerDTO.getQuestionId() + " - Not Found"));
    answer.setTitle(answerDTO.getTitle());
    answer.setIsRight(answerDTO.getIsRight());
    answer.setQuestion(questionId);
    answerRepository.save(answer);
  }

  public void deleteAnswer(long id) {
    answerRepository.findById(id)
        .orElseThrow(() -> new ElementNotFoundException("Answer with id: " + id + " - Not Found"));
    answerRepository.deleteById(id);
  }

}
