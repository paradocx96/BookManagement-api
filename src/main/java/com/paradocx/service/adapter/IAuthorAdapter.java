package com.paradocx.service.adapter;

import com.paradocx.dto.author.AuthorDto;
import com.paradocx.dto.MessageDto;
import com.paradocx.model.Author;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAuthorAdapter {
    ResponseEntity<MessageDto> createAuthor(AuthorDto authorDto);

    ResponseEntity<MessageDto> updateAuthor(Author author);

    ResponseEntity<MessageDto> deleteAuthor(long id);

    ResponseEntity<MessageDto> getAuthor(long id);

    List<Author> getAllAuthors();
}
