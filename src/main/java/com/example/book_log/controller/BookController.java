package com.example.book_log.controller;

import com.example.book_log.dto.response.BookResponse;
import com.example.book_log.model.Author;
import com.example.book_log.model.Book;
import com.example.book_log.service.definition.AuthorService;
import com.example.book_log.service.definition.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping("/all")
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return ResponseEntity.ok(bookService.findAll().stream().map(b -> new BookResponse(
                b.getIsbn(),
                b.getTitle(),
                b.getAuthors().stream().map(Author::getName).toList(),
                b.getYearPublished(),
                b.getPages(),
                b.getGenre(),
                b.getDescription())).toList());
    }

    @PostMapping("/add")
    public ResponseEntity<BookResponse> addBook(@Valid @RequestBody Book book) {
        for (int i=0; i<book.getAuthors().size(); i++){
            if(authorService.findAllByName(book.getAuthors().get(i).getName()).isEmpty()){
                book.getAuthors().set(i, authorService.save(book.getAuthors().get(i)));
            }
        }

        bookService.save(book);

        return ResponseEntity.ok(new BookResponse(book.getIsbn(),
                book.getTitle(),
                book.getAuthors().stream().map(Author::getName).toList(),
                book.getYearPublished(),
                book.getPages(),
                book.getGenre(),
                book.getDescription()));
    }

    @GetMapping("/search/{isbn}")
    public ResponseEntity<BookResponse> searchBook(@PathVariable("isbn") String isbn) {
        Optional<Book> book = bookService.findByIsbn(isbn);
        return book.map(value -> ResponseEntity.ok(new BookResponse(
                        value.getIsbn(),
                        value.getTitle(),
                        value.getAuthors().stream().map(Author::getName).toList(),
                        value.getYearPublished(),
                        value.getPages(),
                        value.getGenre(),
                        value.getDescription())))
                .orElse(ResponseEntity.notFound().build());

    }
}
