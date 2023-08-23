package com.paradocx.service;

import com.paradocx.dto.MessageDto;
import com.paradocx.dto.book.BookDto;
import com.paradocx.model.Author;
import com.paradocx.model.Book;
import com.paradocx.repository.IAuthorRepository;
import com.paradocx.repository.IBookRepository;
import com.paradocx.service.adapter.IBookAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookAdapter {
    private final IBookRepository bookRepository;
    private final IAuthorRepository authorRepository;

    @Autowired
    public BookService(IBookRepository bookRepository, IAuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public ResponseEntity<MessageDto> createBook(BookDto bookDto) {
        System.out.println(bookDto);
        try {
            Book existingIsbn = bookRepository.findByIsbn(bookDto.getIsbn());

            if (existingIsbn != null) {
                return ResponseEntity.ok(new MessageDto("Book with ISBN already exists!", null));
            }

            Author existingAuthor = authorRepository.findById(bookDto.getAuthorId()).orElse(null);

            if (existingAuthor == null) {
                return ResponseEntity.ok(new MessageDto("Author does not exist!", null));
            }

            Book book = new Book();
            book.setIsbn(bookDto.getIsbn());
            book.setTitle(bookDto.getTitle());
            book.setCategory(bookDto.getCategory());
            book.setPublisher(bookDto.getPublisher());
            book.setPrice(bookDto.getPrice());
            book.setPublishedDate(bookDto.getPublishedDate());
            book.setAuthor(existingAuthor);

            book = bookRepository.save(book);

            return ResponseEntity.ok(new MessageDto("Book created successfully!", book));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok(new MessageDto("Error creating book!", null));
    }

    @Override
    public ResponseEntity<MessageDto> updateBook(Book book) {
        try {
            Book existingBook = bookRepository.findById(book.getId()).orElse(null);

            if (existingBook == null) {
                return ResponseEntity.ok(new MessageDto("Book does not exist!", null));
            }

            Author existingAuthor = authorRepository.findById(book.getAuthor().getId()).orElse(null);

            if (existingAuthor == null) {
                return ResponseEntity.ok(new MessageDto("Author does not exist!", null));
            }

            Book existingIsbn = bookRepository.findByIsbn(book.getIsbn());

            if (existingIsbn != null && existingIsbn.getId() != book.getId()) {
                return ResponseEntity.ok(new MessageDto("Book with ISBN already exists!", null));
            }

            existingBook.setIsbn(book.getIsbn());
            existingBook.setTitle(book.getTitle());
            existingBook.setCategory(book.getCategory());
            existingBook.setPublisher(book.getPublisher());
            existingBook.setPrice(book.getPrice());
            existingBook.setPublishedDate(book.getPublishedDate());
            existingBook.setAuthor(existingAuthor);

            existingBook = bookRepository.save(existingBook);

            return ResponseEntity.ok(new MessageDto("Book updated successfully!", existingBook));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok(new MessageDto("Error updating book!", null));
    }

    @Override
    public ResponseEntity<MessageDto> getBook(long id) {
        try {
            Book existingBook = bookRepository.findById(id).orElse(null);

            if (existingBook == null) {
                return ResponseEntity.ok(new MessageDto("Book does not exist!", null));
            }

            return ResponseEntity.ok(new MessageDto("Book retrieved successfully!", existingBook));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok(new MessageDto("Error finding book!", null));
    }

    @Override
    public ResponseEntity<MessageDto> getBookByIsbn(String isbn) {
        try {
            Book existingBook = bookRepository.findByIsbn(isbn);

            if (existingBook == null) {
                return ResponseEntity.ok(new MessageDto("Book does not exist!", null));
            }

            return ResponseEntity.ok(new MessageDto("Book retrieved successfully!", existingBook));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok(new MessageDto("Error finding book!", null));
    }

    @Override
    public ResponseEntity<MessageDto> deleteBook(long id) {
        try {
            Book existingBook = bookRepository.findById(id).orElse(null);

            if (existingBook == null) {
                return ResponseEntity.ok(new MessageDto("Book does not exist!", null));
            }

            bookRepository.deleteById(id);

            return ResponseEntity.ok(new MessageDto("Book deleted successfully!", null));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok(new MessageDto("Error deleting book!", null));
    }

    @Override
    public List<Book> getAllBooks() {
        try {
            return bookRepository.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<Book> getBooksByAuthor(long authorId) {
        try {
            Author existingAuthor = authorRepository.findById(authorId).orElse(null);

            if (existingAuthor == null) {
                return null;
            }

            return bookRepository.findByAuthorId(authorId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
