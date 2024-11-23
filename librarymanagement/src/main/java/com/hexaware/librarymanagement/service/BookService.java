package com.hexaware.librarymanagement.service;

import com.hexaware.librarymanagement.entity.Book;

import java.util.List;

public interface BookService {
    Book addBook(Book book);
    List<Book> searchBooksByTitle(String title);
    List<Book> getBooksByGenre(String genre);
    List<Book> getBooksByAuthor(String author);
    Book getBookById(int bookId);
    List<Book> getAllBooks();


}