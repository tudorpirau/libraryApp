package com.app.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private boolean isBorrowed;

  @ManyToOne private Customer borrowedBy;

  public void setBorrowed(boolean borrowed) {
    isBorrowed = borrowed;
  }

  public boolean isBorrowed() {
    return isBorrowed;
  }

  public void setBorrowedBy(Customer customer) {
    borrowedBy = customer;
  }

  public Customer getBorrowedBy() {
    return borrowedBy;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Long getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
  }
}
