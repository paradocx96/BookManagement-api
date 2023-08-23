package com.paradocx.service;

import com.paradocx.dto.author.AuthorDto;
import com.paradocx.dto.MessageDto;
import com.paradocx.model.Author;
import com.paradocx.model.Book;
import com.paradocx.repository.IAuthorRepository;
import com.paradocx.repository.IBookRepository;
import com.paradocx.service.adapter.IAuthorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements IAuthorAdapter {
    private final IAuthorRepository authorRepository;
    private final IBookRepository bookRepository;

    @Autowired
    public AuthorService(IAuthorRepository authorRepository, IBookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public ResponseEntity<MessageDto> createAuthor(AuthorDto authorDto) {
        try {
            Author author = new Author();
            author.setName(authorDto.getName());
            author.setEmail(authorDto.getEmail());
            author.setContact(authorDto.getContact());

            author = authorRepository.save(author);

            return ResponseEntity.ok(new MessageDto("Author created successfully", author));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok(new MessageDto("Error creating author!", null));
    }

    @Override
    public ResponseEntity<MessageDto> updateAuthor(Author author) {
        try {
            author = authorRepository.save(author);

            return ResponseEntity.ok(new MessageDto("Author updated successfully", author));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok(new MessageDto("Error updating author!", null));
    }

    @Override
    public ResponseEntity<MessageDto> deleteAuthor(long id) {
        try {
            Author existingAuthor = authorRepository.findById(id).orElse(null);

            if (existingAuthor == null) {
                return ResponseEntity.ok(new MessageDto("Author not found!", null));
            }

            List<Book> existingBooks = bookRepository.findByAuthorId(id);

            if (existingBooks != null && !existingBooks.isEmpty()) {
                bookRepository.deleteByAuthorId(id);
            }

            authorRepository.deleteById(id);

            return ResponseEntity.ok(new MessageDto("Author deleted successfully", null));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok(new MessageDto("Error deleting author!", null));
    }

    @Override
    public ResponseEntity<MessageDto> getAuthor(long id) {
        try {
            Author existingAuthor = authorRepository.findById(id).orElse(null);

            if (existingAuthor == null) {
                return ResponseEntity.ok(new MessageDto("Author not found!", null));
            }

            return ResponseEntity.ok(new MessageDto("Author found successfully", existingAuthor));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok(new MessageDto("Error find author!", null));
    }

    @Override
    public List<Author> getAllAuthors() {
        try {
            return authorRepository.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
