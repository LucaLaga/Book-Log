package com.example.book_log.service.impl;

import com.example.book_log.model.Book;
import com.example.book_log.model.BookOwned;
import com.example.book_log.model.User;
import com.example.book_log.repository.BookOwnedRepo;
import com.example.book_log.repository.BookRepo;
import com.example.book_log.repository.UserRepo;
import com.example.book_log.service.definition.BookOwnedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookOwnedServiceImpl implements BookOwnedService {
    private final BookOwnedRepo bookOwnedRepo;
    private final BookRepo bookRepo;
    private final UserRepo userRepo;

    @Override
    public Optional<BookOwned> findById(Long id) {
        return bookOwnedRepo.findById(id);
    }

    @Override
    public List<BookOwned> findAllByUserId(Long userId) {
        return bookOwnedRepo.findAllByUserId(userId);
    }

    @Override
    public List<BookOwned> findAllByBookId(Long bookId) {
        return bookOwnedRepo.findAllByBookId(bookId);
    }

    @Override
    public Optional<BookOwned> findByUserId(Long userId) {
        return bookOwnedRepo.findByUserId(userId);
    }

    @Override
    public Optional<BookOwned> findByBookId(Long bookId) {
        return bookOwnedRepo.findByBookId(bookId);
    }

    @Override
    public Optional<BookOwned> findByUserEmail(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        return user.map(value -> bookOwnedRepo.findByUserId(value.getId())).orElse(null);
    }

    @Override
    public Optional<BookOwned> findByBookIsbn(String isbn) {
        Optional<Book> book = bookRepo.findByIsbn(isbn);
        return book.map(value -> bookOwnedRepo.findByBookId(value.getId())).orElse(null);
    }

    @Override
    public Optional<BookOwned> findByUserName(String userName) {
        Optional<User> user = userRepo.findByEmail(userName);
        return user.map(value -> bookOwnedRepo.findByUserId(value.getId())).orElse(null);
    }

    @Override
    public Optional<BookOwned> findByBookTitle(String bookTitle) {
        Optional<Book> book = bookRepo.findByTitle(bookTitle);
        return book.map(value -> bookOwnedRepo.findByBookId(value.getId())).orElse(null);
    }

    @Override
    public Optional<BookOwned> findByBookIsbnAndUserEmail(String bookIsbn, String userEmail) {
        System.out.println("Book Owned Search");
        Optional<Book> book = bookRepo.findByIsbn(bookIsbn);
        Optional<User> user = userRepo.findByEmail(userEmail);

        if(book.isEmpty()){
            System.out.println("Book empty");
            return Optional.empty();
        }

        if(user.isEmpty()){
            System.out.println("User empty");
            return Optional.empty();
        }

        return bookOwnedRepo.findByBookIdAndUserId(book.get().getId(), user.get().getId());
    }

    @Override
    public BookOwned save(BookOwned bookOwned) {
        return bookOwnedRepo.save(bookOwned);
    }

    @Override
    public void delete(BookOwned bookOwned) {
        bookOwnedRepo.delete(bookOwned);
    }
}
