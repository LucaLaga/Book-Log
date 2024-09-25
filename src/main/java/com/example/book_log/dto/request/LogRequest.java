package com.example.book_log.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogRequest {
    @NotBlank(message = "L'iabn non può essere nullo o vuoto")
    private String isbn;
    @NotNull(message = "La mail non può essere nulla")
    @Email(message = "Mail non valida")
    private String userEmail;
    private LocalDate date;
    @Min(1)
    private int pages;
    @Min(1)
    private double minutes;
}
