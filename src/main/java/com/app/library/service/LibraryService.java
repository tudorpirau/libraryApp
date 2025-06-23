package com.app.library.service;

import com.app.library.model.Book;
import com.app.library.model.Customer;
import com.app.library.repository.BookRepository;
import com.app.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

  @Autowired private BookRepository bookRepo;

  @Autowired private UserRepository userRepo;

  public Book addBook(Book book) {
    return bookRepo.save(book);
  }

  public Book borrowBook(Long bookId, Long userId) {
    Book book = bookRepo.findById(bookId).orElse(null);
    Customer customer = userRepo.findById(userId).orElse(null);
    if (book.isBorrowed()) throw new RuntimeException("Book already borrowed");

    book.setBorrowed(true);
    book.setBorrowedBy(customer);
    return bookRepo.save(book);
  }

  public Book returnBook(Long bookId) {
    Book book = bookRepo.findById(bookId).orElse(null);
    book.setBorrowed(false);
    book.setBorrowedBy(null);
    return bookRepo.save(book);
  }

  public List<Book> getBorrowedBooks(Long userId) {
    return bookRepo.findByBorrowedBy_Id(userId);
  }

  public Customer createCustomer(Customer customer) {
    return userRepo.save(customer);
  }

  public List<Book> getAllBooks() {
    return bookRepo.findAll();
  }

  public List<Customer> getAllCustomers() {
    return userRepo.findAll();
  }
}
