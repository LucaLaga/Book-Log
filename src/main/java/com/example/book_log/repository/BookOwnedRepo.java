package com.example.book_log.repository;

import com.example.book_log.model.BookOwned;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookOwnedRepo extends JpaRepository<BookOwned, Long> {
    List<BookOwned> findAllByUserId(Long userId);
    List<BookOwned> findAllByBookId(Long bookId);
    Optional<BookOwned> findByBookId(Long bookId);
    Optional<BookOwned> findByUserId(Long userId);
    Optional<BookOwned> findByBookIdAndUserId(Long bookId, Long userId);
}
