package com.litequizapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "answers")
public class AnswerEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answer_seq")
  @SequenceGenerator(name = "answer_seq", sequenceName = "answer_sequence", allocationSize = 1)
  @Column(name = "answer_id")
  private Long id;

  @NonNull
  @Column(name = "answer_title")
  private String title;

  @Column(name = "is_right")
  private Boolean isRight = false;

  @OneToOne
  @JoinColumn(name = "question_id")
  private QuestionEntity question;

  public AnswerEntity(String title, Boolean isRight, QuestionEntity question) {
    this.title = title;
    this.isRight = isRight;
    this.question = question;
  }

}
