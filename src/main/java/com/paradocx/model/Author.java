package com.paradocx.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String name;
    private String email;
    private String contact;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private List<Book> books = new ArrayList<>();
}
