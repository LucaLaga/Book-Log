package com.example.book_log.service.definition;

import com.example.book_log.model.Log;

import java.util.List;
import java.util.Optional;

public interface LogService {
    Optional<Log> findById(Long id);
    Optional<Log> findByUserIdAndBookId(Long userId, Long bookId);
    List<Log> findAllByUserId(Long userId);
    Optional<Log> findByUserEmailAndBookIsbn(String email, String bookIsbn);
    Log save(Log log);
    void delete(Log log);
}
