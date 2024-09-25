package com.example.book_log.service.impl;

import com.example.book_log.model.Log;
import com.example.book_log.repository.LogRepo;
import com.example.book_log.service.definition.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {
    private final LogRepo logRepo;

    @Override
    public Optional<Log> findById(Long id) {
        return logRepo.findById(id);
    }

    @Override
    public Optional<Log> findByUserIdAndBookId(Long userId, Long bookId) {
        return logRepo.findByBookIdAndUserId(bookId, userId);
    }

    @Override
    public List<Log> findAllByUserId(Long userId) {
        return logRepo.findAllByUserId(userId);
    }

    @Override
    public Optional<Log> findByUserEmailAndBookIsbn(String email, String bookIsbn) {
        return logRepo.findByUser_EmailAndBook_Book_Isbn(email, bookIsbn);
    }

    @Override
    public Log save(Log log) {
        return logRepo.save(log);
    }

    @Override
    public void delete(Log log) {
        logRepo.delete(log);
    }
}
