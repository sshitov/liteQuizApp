package com.litequizapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "questions")
public class QuestionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "question_seq")
  @SequenceGenerator(name = "question_seq",
      sequenceName = "question_sequence", allocationSize = 1)
  @Column(name = "id")
  @Getter
  private Long id;

  @Column(name = "title")
  @Getter
  @Setter
  private String title;

  protected QuestionEntity() {
  }

  public QuestionEntity(String title) {

    this.title = title;

  }

  @Override
  public String toString() {
    return String.format("Question[id='%d', title='%s']", id, title);
  }

}
