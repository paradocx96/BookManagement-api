package com.paradocx.controller;

import com.paradocx.dto.MessageDto;
import com.paradocx.dto.book.BookDto;
import com.paradocx.model.Book;
import com.paradocx.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageDto> createBook(@RequestBody BookDto bookDto) {
        return bookService.createBook(bookDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageDto> getBookById(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    @GetMapping("/author/{authorId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getBooksByAuthor(@PathVariable Long authorId) {
        return bookService.getBooksByAuthor(authorId);
    }

    @GetMapping("/isbn/{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageDto> getBookByIsbn(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageDto> updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageDto> deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }
}
