package com.example.book_log.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Role role;
    @OneToMany
    private List<BookOwned> booksOwned;;
    @OneToMany
    private List<Log> logs;

    public User(String firstName, String lastName, String email, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = firstName + "." + lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = firstName + "." + lastName;
        this.email = email;
        this.password = password;
        this.role = Role.USER;
    }

    public void addBookOwned(BookOwned bookOwned) {
        if (this.booksOwned == null) this.booksOwned = new ArrayList<>();
        this.booksOwned.add(bookOwned);
    }

    public void removeBookOwned(BookOwned bookOwned) {
        if (this.booksOwned == null) return;
        this.booksOwned.remove(bookOwned);
    }

    public void addLog(Log log) {
        if (this.logs == null) this.logs = new ArrayList<>();
        this.logs.add(log);
    }

    public void removeLog(Log log) {
        if (this.logs == null) return;
        this.logs.remove(log);
    }
}
