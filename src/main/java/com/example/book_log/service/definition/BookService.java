package com.example.book_log.service.definition;

import com.example.book_log.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Optional<Book> findByIsbn(String isbn);
    Optional<Book> findByTitle(String title);
    Book save(Book book);
}
