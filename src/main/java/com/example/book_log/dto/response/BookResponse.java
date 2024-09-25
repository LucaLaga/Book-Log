package com.example.book_log.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private String isbn;
    private String title;
    private List<String> authors;
    private String yearPublished;
    private int pages;
    private String genre;
    private String description;
}
