package com.paradocx.service.adapter;

import com.paradocx.dto.MessageDto;
import com.paradocx.dto.book.BookDto;
import com.paradocx.model.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IBookAdapter {
    ResponseEntity<MessageDto> createBook(BookDto bookDto);

    ResponseEntity<MessageDto> updateBook(Book book);

    ResponseEntity<MessageDto> getBook(long id);

    ResponseEntity<MessageDto> getBookByIsbn(String isbn);

    ResponseEntity<MessageDto> deleteBook(long id);

    List<Book> getAllBooks();

    List<Book> getBooksByAuthor(long authorId);
}
