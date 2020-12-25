package com.litequizapp.server;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class CategoryEntity {

  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="categories_seq")
  @SequenceGenerator(
      name="categories_seq",
      sequenceName="categories_sequence",
      allocationSize=1
  )
  private Long id;

  @Column(name = "title")
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

  public void setTitle(String title){
    this.title = title;
  }

  @Override
  public String toString() {
    return String.format("Category[id='%d', title='%s']", id, title);
  }

}
