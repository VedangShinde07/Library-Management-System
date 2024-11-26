package com.hexaware.librarymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private int bookId;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private Date publicationDate;
    private String edition;
    private String genre;
    private String description;
    private String language;
    private int numberOfPages;
    private double cost;
    private boolean available;
}
