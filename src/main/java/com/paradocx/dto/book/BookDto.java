package com.paradocx.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class BookDto {
    private String isbn;
    private String title;
    private String category;
    private String publisher;
    private double price;
    private Date publishedDate;
    private long authorId;
}
