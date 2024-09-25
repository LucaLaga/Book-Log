package com.example.book_log.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookOwnedRequest {
    @NotBlank(message = "L'isbn non può essere null o vuoto")
    private String bookIsbn;
    @NotNull(message = "La mail non può essere nulla")
    @Email(message = "Mail non valida")
    private String userEmail;
}
