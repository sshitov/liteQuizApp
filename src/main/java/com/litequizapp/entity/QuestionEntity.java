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
  @Column(name = "question_id")
  @Getter
  private Long id;

  @Column(name = "question_title")
  @Getter
  @Setter
  @NonNull
  private String title;

  @Getter
  @Setter
  @OneToOne
  @JoinColumn(name = "category_id")
  private CategoryEntity category;

  public QuestionEntity(String title, CategoryEntity category) {
    this.title = title;
    this.category = category;

  }

  @Override
  public String toString() {
    return String.format("Question[id='%d', title='%s']", id, title);
  }

}
