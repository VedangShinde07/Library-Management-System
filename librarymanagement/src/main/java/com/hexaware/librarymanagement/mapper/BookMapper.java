package com.hexaware.librarymanagement.mapper;

import com.hexaware.librarymanagement.dto.BookDTO;
import com.hexaware.librarymanagement.entity.Book;

public class BookMapper {

    // Method to map BookDTO to Book entity
    public static Book mapToBook(BookDTO dto) {
        Book book = new Book();
        book.setBookId(dto.getBookId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setPublisher(dto.getPublisher());
        book.setPublicationDate(dto.getPublicationDate());
        book.setEdition(dto.getEdition());
        book.setGenre(dto.getGenre());
        book.setDescription(dto.getDescription());
        book.setLanguage(dto.getLanguage());
        book.setNumberOfPages(dto.getNumberOfPages());
        book.setCost(dto.getCost());
        book.setAvailable(dto.isAvailable());
        return book;
    }

    // Method to map Book entity to BookDTO
    public static BookDTO mapToBookDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setBookId(book.getBookId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setIsbn(book.getIsbn());
        dto.setPublisher(book.getPublisher());
        dto.setPublicationDate(book.getPublicationDate());
        dto.setEdition(book.getEdition());
        dto.setGenre(book.getGenre());
        dto.setDescription(book.getDescription());
        dto.setLanguage(book.getLanguage());
        dto.setNumberOfPages(book.getNumberOfPages());
        dto.setCost(book.getCost());
        dto.setAvailable(book.isAvailable());
        return dto;
    }
}
