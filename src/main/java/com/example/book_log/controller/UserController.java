package com.example.book_log.controller;

import com.example.book_log.dto.request.UserRequest;
import com.example.book_log.dto.response.LogResponse;
import com.example.book_log.dto.response.UserResponse;
import com.example.book_log.model.BookOwned;
import com.example.book_log.model.User;
import com.example.book_log.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/email/{email}")
    public UserResponse findByEmail(@PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);
        if (user.isEmpty()) return null;

        return new UserResponse(user.get().getFirstName(),
                user.get().getLastName(),
                user.get().getEmail(),
                user.get().getBooksOwned().stream().map(b->b.getBook().getTitle()).toList(),
                user.get().getLogs().stream().map(l -> new LogResponse(l.getBook().getBook().getIsbn(),
                        l.getBook().getBook().getTitle(),
                        l.getDate(),
                        l.getPages(),
                        l.getMinutes())).toList());
    }

    @GetMapping("/username/{username}")
    public User findByUsername(@PathVariable String username) {
        return userService.findByUsername(username).orElse(null);
    }

    @PostMapping("/add")
    public User add(@RequestBody UserRequest userRequest) {
        return userService.save(new User(
                userRequest.getFirstName(),
                userRequest.getLastName(),
                userRequest.getEmail(),
                userRequest.getPassword()));
    }
}
