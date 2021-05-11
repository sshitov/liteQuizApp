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
@Table(name = "questions")
public class QuestionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_seq")
  @SequenceGenerator(name = "question_seq", sequenceName = "question_sequence", allocationSize = 1)
  @Column(name = "question_id")
  private Long id;

  @NonNull
  @Column(name = "question_title")
  private String title;

  @OneToOne
  @JoinColumn(name = "category_id")
  private CategoryEntity category;

  public QuestionEntity(String title, CategoryEntity category) {
    this.title = title;
    this.category = category;
  }

}
