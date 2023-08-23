package com.paradocx.repository;

import com.paradocx.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
    Book findByIsbn(String isbn);

    void deleteByAuthorId(long authorId);

    List<Book> findByAuthorId(long authorId);
}
