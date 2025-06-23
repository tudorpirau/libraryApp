package com.app.library.controller;

import com.app.library.model.Book;
import com.app.library.model.Customer;
import com.app.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LibraryController {
  @Autowired private LibraryService service;

  @GetMapping("/")
  public String home() {
    return "index";
  }

  @PostMapping("/library/addBook")
  public String addBook(@RequestParam("title") String title) {
    Book book = new Book();
    book.setTitle(title);
    book.setBorrowed(false);
    service.addBook(book);
    return "redirect:/";
  }

  @PostMapping("/library/borrow")
  public String borrowBook(
      @RequestParam("bookId") Long bookId, @RequestParam("customerId") Long customerId) {
    service.borrowBook(bookId, customerId);
    return "redirect:/";
  }

  @PostMapping("/library/return")
  public String returnBook(@RequestParam("bookId") Long bookId) {
    service.returnBook(bookId);
    return "redirect:/";
  }

  @GetMapping("/library/myBooks")
  public String myBooks(@RequestParam("customerId") Long customerId, Model model) {
    model.addAttribute("borrowedBooks", service.getBorrowedBooks(customerId));
    return "index";
  }

  @GetMapping("/library/allBooks")
  public String allBooks(Model model) {
    List<Book> books = service.getAllBooks();
    model.addAttribute("books", books);
    return "books";
  }

  @PostMapping("/library/addCustomer")
  public String addCustomer(
      @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
    Customer customer = new Customer();
    customer.setFirstName(firstName);
    customer.setLastName(lastName);
    service.createCustomer(customer);
    return "redirect:/";
  }

  @GetMapping("/library/allCustomers")
  public String allCustomers(Model model) {
    List<Customer> customers = service.getAllCustomers();
    model.addAttribute("customers", customers);
    return "customers";
  }
}
