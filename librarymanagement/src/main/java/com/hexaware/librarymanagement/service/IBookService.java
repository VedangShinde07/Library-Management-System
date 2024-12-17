package com.hexaware.librarymanagement.service;

import com.hexaware.librarymanagement.dto.BookDTO;

import java.util.List;

public interface IBookService {
    BookDTO addBook(BookDTO bookDTO);
    List<BookDTO> searchBooksByTitle(String title);
    List<BookDTO> getBooksByGenre(String genre);
    List<BookDTO> getBooksByAuthor(String author);
    List<BookDTO> getAllBooks();
    BookDTO getBookById(int bookId);
    BookDTO updateBook(int bookId, BookDTO bookDTO); // Updates book details by bookId
    void deleteBook(int bookId);
}
