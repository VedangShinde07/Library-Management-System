package com.hexaware.librarymanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false, unique = true)
    private String isbn;

    private String publisher;
    private Date publicationDate;
    private String edition;
    private String genre;
    private String description;
    private String language;

    @Column(nullable = false)
    private int numberOfPages;

    @Column(nullable = false)
    private double cost;

    @Column(nullable = false)
    private boolean available; // true if the book is available

    @Version
    private int version;

    public Book(String title, String author, String isbn, String publisher, Date publicationDate,
                String edition, String genre, String description, String language,
                int numberOfPages, double cost, boolean available) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.edition = edition;
        this.genre = genre;
        this.description = description;
        this.language = language;
        this.numberOfPages = numberOfPages;
        this.cost = cost;
        this.available = available;
    }
}