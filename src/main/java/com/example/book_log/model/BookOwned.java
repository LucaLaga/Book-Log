package com.example.book_log.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books_owned", uniqueConstraints = {@UniqueConstraint(columnNames = {"book_id", "user_id"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookOwned {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Book book;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @OneToMany
    private List<Log> logs;
    private int pages_read;
    private double progress;

    public BookOwned(Book book, User user) {
        this.book = book;
        this.user = user;
    }

    public void addLog(Log log){
        if (this.logs == null) this.logs = new ArrayList<>();
        this.logs.add(log);
    }

    public void removeLog(Log log){
        if (this.logs != null) this.logs.remove(log);
    }

    public void addPages(int pages_read){
        this.pages_read = pages_read;
        this.progress = ((double) pages_read / this.book.getPages()) * 100;
    }
}
