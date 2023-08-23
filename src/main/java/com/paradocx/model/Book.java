package com.paradocx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String isbn;
    private String title;
    private String category;
    private String publisher;
    private double price;
    private Date publishedDate;

    @JsonBackReference
    @JsonProperty("author")
    @JoinColumn(name = "author_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Author author;
}
