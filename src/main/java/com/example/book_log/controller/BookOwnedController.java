package com.example.book_log.controller;

import com.example.book_log.dto.request.BookOwnedRequest;
import com.example.book_log.dto.response.LogResponse;
import com.example.book_log.dto.response.UserResponse;
import com.example.book_log.model.Book;
import com.example.book_log.model.BookOwned;
import com.example.book_log.model.User;
import com.example.book_log.service.definition.BookOwnedService;
import com.example.book_log.service.definition.BookService;
import com.example.book_log.service.definition.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookOwned")
public class BookOwnedController {
    private final BookOwnedService bookOwnedService;
    private final BookService bookService;
    private final UserService userService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<BookOwned> getBookOwnedByUserId(@PathVariable Long userId) {
        return bookOwnedService.findByUserId(userId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<BookOwned> getBookOwnedByBookId(@PathVariable Long bookId) {
        return bookOwnedService.findByBookId(bookId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/all/{userId}")
    public ResponseEntity<List<BookOwned>> getAllBookOwnedByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(bookOwnedService.findAllByUserId(userId));
    }

    @GetMapping("/book/all/{bookId}")
    public ResponseEntity<List<BookOwned>> getAllBookOwnedByBookId(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookOwnedService.findAllByBookId(bookId));
    }

    @PostMapping("/add")
    public ResponseEntity<UserResponse> addBookOwned(@Valid @RequestBody BookOwnedRequest bookOwnedRequest) {
        Optional<BookOwned> bookOwned = bookOwnedService.findByBookIsbnAndUserEmail(bookOwnedRequest.getBookIsbn(), bookOwnedRequest.getUserEmail());
        if (bookOwned.isPresent()) {
            System.out.println("Book owned: " + bookOwned.get());
            return ResponseEntity.badRequest().build();
        }
        Optional<Book> book = bookService.findByIsbn(bookOwnedRequest.getBookIsbn());
        Optional<User> user = userService.findByEmail(bookOwnedRequest.getUserEmail());

        if (user.isEmpty() || book.isEmpty()) return ResponseEntity.badRequest().build();

        BookOwned bookToAdd = new BookOwned(book.get(), user.get());

        bookToAdd = bookOwnedService.save(bookToAdd);

        user.get().addBookOwned(bookToAdd);
        book.get().addOwned(bookToAdd);

        userService.save(user.get());
        bookService.save(book.get());

        return ResponseEntity.ok(new UserResponse(
                user.get().getFirstName(),
                user.get().getLastName(),
                user.get().getEmail(),
                user.get().getBooksOwned().stream().map(u -> u.getBook().getTitle()).toList(),
                user.get().getLogs().stream().map(l -> new LogResponse(l.getBook().getBook().getIsbn(),
                        l.getBook().getBook().getTitle(),
                        l.getDate(),
                        l.getPages(),
                        l.getMinutes())).toList()
        ));
    }

    @PostMapping("/remove")
    public ResponseEntity<UserResponse> removeBookOwned(@Valid @RequestBody BookOwnedRequest bookOwnedRequest) {
        Optional<BookOwned> bookOwned = bookOwnedService.findByBookIsbnAndUserEmail(bookOwnedRequest.getBookIsbn(), bookOwnedRequest.getUserEmail());
        Optional<User> user = userService.findByEmail(bookOwnedRequest.getUserEmail());
        Optional<Book> book = bookService.findByIsbn(bookOwnedRequest.getBookIsbn());

        if (bookOwned.isEmpty() || user.isEmpty() || book.isEmpty()) return ResponseEntity.badRequest().build();

        user.get().removeBookOwned(bookOwned.get());
        book.get().removeOwned(bookOwned.get());

        bookOwnedService.delete(bookOwned.get());

        userService.save(user.get());
        bookService.save(book.get());

        return ResponseEntity.ok(new UserResponse(
                user.get().getFirstName(),
                user.get().getLastName(),
                user.get().getEmail(),
                user.get().getBooksOwned().stream().map(u -> u.getBook().getTitle()).toList(),
                user.get().getLogs().stream().map(l -> new LogResponse(l.getBook().getBook().getIsbn(),
                        l.getBook().getBook().getTitle(),
                        l.getDate(),
                        l.getPages(),
                        l.getMinutes())).toList()
        ));
    }
}
