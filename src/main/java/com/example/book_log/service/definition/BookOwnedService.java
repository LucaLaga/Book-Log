package com.example.book_log.service.definition;

import com.example.book_log.model.BookOwned;

import java.util.List;
import java.util.Optional;

public interface BookOwnedService {
    Optional<BookOwned> findById(Long id);
    List<BookOwned> findAllByUserId(Long userId);
    List<BookOwned> findAllByBookId(Long bookId);
    Optional<BookOwned> findByUserId(Long userId);
    Optional<BookOwned> findByBookId(Long bookId);
    Optional<BookOwned> findByUserEmail(String email);
    Optional<BookOwned> findByBookIsbn(String isbn);
    Optional<BookOwned> findByUserName(String userName);
    Optional<BookOwned> findByBookTitle(String bookTitle);
    Optional<BookOwned> findByBookIsbnAndUserEmail(String bookIsbn, String userEmail);
    BookOwned save(BookOwned bookOwned);
    void delete(BookOwned bookOwned);
}
