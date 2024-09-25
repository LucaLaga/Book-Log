package com.example.book_log.repository;

import com.example.book_log.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LogRepo extends JpaRepository<Log, Long> {
    Optional<Log> findByBookIdAndUserId(Long bookId, Long userId);
    List<Log> findAllByUserId(Long userId);
    Optional<Log> findByUser_EmailAndBook_Book_Isbn(String email, String bookIsbn);
}
