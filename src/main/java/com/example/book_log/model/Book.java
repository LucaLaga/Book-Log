package com.example.book_log.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String title;
    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;
    private String yearPublished;
    private int pages;
    private String genre;
    @Lob
    @Column(length = 512)
    private String description;
    @OneToMany(mappedBy = "book")
    private List<BookOwned> owned;

    public Book(String isbn, String title, List<Author> authors, String yearPublished, int pages, String genre, String description) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.yearPublished = yearPublished;
        this.pages = pages;
        this.genre = genre;
        this.description = description;
    }

    public void addOwned(BookOwned bookOwned) {
        if (this.owned == null) this.owned = new ArrayList<>();
        this.owned.add(bookOwned);
    }

    public void removeOwned(BookOwned bookOwned) {
        if (this.owned == null) return;
        this.owned.remove(bookOwned);
    }
}
