package com.example.book_log.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotBlank(message = "Il nome non può essere nullo o vuoto")
    private String firstName;
    @NotBlank(message = "Il cognome non può essere nullo o vuoto")
    private String lastName;
    @NotBlank(message = "L'username non può essere nullo o vuoto")
    private String username;
    @NotNull(message = "La mail non può essere nulla")
    @Email(message = "Mail Non valida")
    private String email;
    @Pattern(regexp = "^.(?=.{8,})(?=..[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=]).$")
    private String password;
}
