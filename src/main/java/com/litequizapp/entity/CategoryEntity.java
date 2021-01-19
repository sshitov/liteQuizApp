package com.litequizapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categories")
public class CategoryEntity {

  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE)
  @Column(name = "id")
  @Getter
  private Long id;

  @Column(name = "title")
  @Getter
  @Setter
  private String title;

  protected CategoryEntity() {
  }

  public CategoryEntity(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return String.format("Category[id='%d', title='%s']", id, title);
  }

}
