package com.litequizapp.server;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CategoryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String title;

  protected CategoryEntity() {
  }

  public CategoryEntity(String title) {
    this.title = title;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  @Override
  public String toString() {
    return String.format("Category[id='%d', title='%s']", id, title);
  }

}
