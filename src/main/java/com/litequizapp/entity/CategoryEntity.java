package com.litequizapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "categories")
public class CategoryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
  @SequenceGenerator(name = "category_seq", sequenceName = "category_sequence", allocationSize = 1)
  @Column(name = "category_id")
  private Long id;

  @NonNull
  @Column(name = "category_title")
  private String title;

}
