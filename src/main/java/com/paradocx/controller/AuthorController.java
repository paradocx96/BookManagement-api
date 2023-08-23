package com.paradocx.controller;

import com.paradocx.dto.author.AuthorDto;
import com.paradocx.dto.MessageDto;
import com.paradocx.model.Author;
import com.paradocx.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/author")
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageDto> createAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.createAuthor(authorDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageDto> getAuthorById(@PathVariable Long id) {
        return authorService.getAuthor(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageDto> updateAuthor(@RequestBody Author author) {
        return authorService.updateAuthor(author);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageDto> deleteAuthor(@PathVariable Long id) {
        return authorService.deleteAuthor(id);
    }
}
