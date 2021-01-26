package com.litequizapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@RequiredArgsConstructor
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
  @NonNull
  private String title;


  @Override
  public String toString() {
    return String.format("Question[id='%d', title='%s']", id, title);
  }

}
