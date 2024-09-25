package com.example.book_log.service.impl;

import com.example.book_log.model.Book;
import com.example.book_log.repository.BookRepo;
import com.example.book_log.service.definition.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepo bookRepo;

    @Override
    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepo.findById(id);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return bookRepo.findByIsbn(isbn);
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        return bookRepo.findByTitle(title);
    }

    @Override
    public Book save(Book book) {
        return bookRepo.save(book);
    }
}
