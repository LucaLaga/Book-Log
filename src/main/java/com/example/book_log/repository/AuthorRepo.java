package com.example.book_log.repository;

import com.example.book_log.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepo extends JpaRepository<Author,Long> {
    List<Author> findAllByName(String name);
}
