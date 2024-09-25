package com.example.book_log.service.impl;

import com.example.book_log.model.Author;
import com.example.book_log.repository.AuthorRepo;
import com.example.book_log.service.definition.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepo authorRepo;

    @Override
    public Author save(Author author) {
        return authorRepo.save(author);
    }

    @Override
    public List<Author> findAllByName(String name) {
        return authorRepo.findAllByName(name);
    }
}
