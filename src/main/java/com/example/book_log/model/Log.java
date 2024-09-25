package com.example.book_log.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "logs", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id","book_id"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private BookOwned book;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private LocalDate date;
    private int pages;
    private double minutes;

    public Log(BookOwned book, User user, LocalDate date, int pages, double minutes) {
        this.book = book;
        this.user = user;
        this.date = date;
        this.pages = pages;
        this.minutes = minutes;
    }
}
