package com.example.book_log.controller;

import com.example.book_log.dto.request.LogRequest;
import com.example.book_log.dto.response.LogResponse;
import com.example.book_log.model.BookOwned;
import com.example.book_log.model.Log;
import com.example.book_log.model.User;
import com.example.book_log.service.definition.BookOwnedService;
import com.example.book_log.service.definition.LogService;
import com.example.book_log.service.definition.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/log")
public class LogController {
    private final LogService logService;
    private final UserService userService;
    private final BookOwnedService bookOwnedService;

    @GetMapping("/user/all/{userId}")
    public List<LogResponse> getAllUserLog(@PathVariable Long userId) {
        List<Log> logs = logService.findAllByUserId(userId);
        if (logs.isEmpty()) return null;

        List<LogResponse> logResponses = new ArrayList<>();

        for (Log log : logs) {
            logResponses.add(new LogResponse(log.getBook().getBook().getIsbn(),
                    log.getBook().getBook().getTitle(),
                    log.getDate(),
                    log.getPages(),
                    log.getMinutes()));
        }

        return logResponses;
    }

    @PostMapping("/add")
    public ResponseEntity<LogResponse> add(@Valid @RequestBody LogRequest logRequest) {
        Optional<BookOwned> bookOwned = bookOwnedService.findByBookIsbnAndUserEmail(logRequest.getIsbn(), logRequest.getUserEmail());
        Optional<User> user = userService.findByEmail(logRequest.getUserEmail());

        if(bookOwned.isEmpty()) {
            System.out.println("BookOwned empty");
            return ResponseEntity.badRequest().build();
        }

        if(user.isEmpty()){
            System.out.println("User empty");
            return ResponseEntity.badRequest().build();
        }

        Log log = new Log(
                bookOwned.get(),
                user.get(),
                logRequest.getDate(),
                logRequest.getPages(),
                logRequest.getMinutes()
        );

        log = logService.save(log);

        user.get().addLog(log);
        bookOwned.get().addLog(log);

        bookOwned.get().addPages(log.getPages());

        userService.save(user.get());
        return ResponseEntity.ok(new LogResponse(bookOwned.get().getBook().getIsbn(),
                bookOwned.get().getBook().getTitle(),
                logRequest.getDate(),
                logRequest.getPages(),
                logRequest.getMinutes()));
    }

    @PostMapping("/remove")
    public ResponseEntity<LogResponse> remove(@Valid @RequestBody LogRequest logRequest) {
        Optional<BookOwned> bookOwned = bookOwnedService.findByBookIsbnAndUserEmail(logRequest.getIsbn(), logRequest.getUserEmail());
        Optional<User> user = userService.findByEmail(logRequest.getUserEmail());

        if(bookOwned.isEmpty() || user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Log> log = logService.findByUserEmailAndBookIsbn(logRequest.getUserEmail(), bookOwned.get().getBook().getIsbn());

        if(log.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        userService.save(user.get());
        return ResponseEntity.ok(new LogResponse(bookOwned.get().getBook().getIsbn(),
                bookOwned.get().getBook().getTitle(),
                logRequest.getDate(),
                logRequest.getPages(),
                logRequest.getMinutes()));
    }
}
