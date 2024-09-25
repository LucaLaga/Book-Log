package com.example.book_log.service.definition;

import com.example.book_log.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Author save(Author author);
    List<Author> findAllByName(String name);
}
