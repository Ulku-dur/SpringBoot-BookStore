package com.bookstore.bookstore.service;

import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BookService {
    @Autowired
   private BookRepository bookRepo;
    public void save(Book book) {
        bookRepo.save(book);

    }
    public List<Book> getAllBook() {
        return bookRepo.findAll();
    }
    public Book getBookById(int id) {
        return bookRepo.findById(id).orElseThrow();
    }
    public void deleteById(int id) {
        bookRepo.deleteById(id);
    }
}
