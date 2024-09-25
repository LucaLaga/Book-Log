package com.example.book_log.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogResponse {
    private String bookIsbn;
    private String bookTitle;
    private LocalDate date;
    private int pages;
    private double minutes;
}
