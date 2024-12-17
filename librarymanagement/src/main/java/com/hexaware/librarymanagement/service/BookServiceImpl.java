package com.hexaware.librarymanagement.service;

import com.hexaware.librarymanagement.dto.BookDTO;
import com.hexaware.librarymanagement.entity.Book;
import com.hexaware.librarymanagement.exception.CRUDAPIException;
import com.hexaware.librarymanagement.mapper.BookMapper;
import com.hexaware.librarymanagement.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        if (bookDTO.getTitle() == null || bookDTO.getTitle().trim().isEmpty()) {
            throw new CRUDAPIException(HttpStatus.BAD_REQUEST, "Invalid Book Data", "Book title cannot be null or empty.");
        }
        // Map DTO to entity and save
        Book book = BookMapper.mapToBook(bookDTO);
        Book savedBook = bookRepository.save(book);

        // Return the saved entity as DTO
        return BookMapper.mapToBookDTO(savedBook);
    }

    @Override
    public List<BookDTO> searchBooksByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new CRUDAPIException(HttpStatus.BAD_REQUEST, "Invalid Search Query", "Search title cannot be null or empty.");
        }
        // Find books by title and map to DTOs
        List<Book> books = bookRepository.findByTitleContaining(title);
        return books.stream()
                .map(BookMapper::mapToBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getBooksByGenre(String genre) {
        if (genre == null || genre.trim().isEmpty()) {
            throw new CRUDAPIException(HttpStatus.BAD_REQUEST, "Invalid Genre", "Genre cannot be null or empty.");
        }
        // Find books by genre and map to DTOs
        List<Book> books = bookRepository.findByGenre(genre);
        return books.stream()
                .map(BookMapper::mapToBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getBooksByAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new CRUDAPIException(HttpStatus.BAD_REQUEST, "Invalid Author", "Author cannot be null or empty.");
        }
        // Find books by author and map to DTOs
        List<Book> books = bookRepository.findByAuthor(author);
        return books.stream()
                .map(BookMapper::mapToBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getAllBooks() {
        // Retrieve all books and map to DTOs
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            throw new CRUDAPIException(HttpStatus.NOT_FOUND, "No Books Found", "There are no books available in the system.");
        }
        return books.stream()
                .map(BookMapper::mapToBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(int bookId) {
        if (bookId <= 0) {
            throw new CRUDAPIException(HttpStatus.BAD_REQUEST, "Invalid Book ID", "Book ID must be greater than 0.");
        }
        // Find book by ID and map to DTO
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new CRUDAPIException(HttpStatus.NOT_FOUND, "Book Not Found", "No book found with ID: " + bookId));
        return BookMapper.mapToBookDTO(book);
    }

    @Transactional
    @Override
    public BookDTO updateBook(int bookId, BookDTO bookDTO) {
        if (bookId <= 0) {
            throw new CRUDAPIException(HttpStatus.BAD_REQUEST, "Invalid Book ID", "Book ID must be greater than 0.");
        }

        if (bookDTO.getTitle() == null || bookDTO.getTitle().trim().isEmpty()) {
            throw new CRUDAPIException(HttpStatus.BAD_REQUEST, "Invalid Book Data", "Book title cannot be null or empty.");
        }

        // Fetch the existing book
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new CRUDAPIException(HttpStatus.NOT_FOUND, "Book Not Found", "No book found with ID: " + bookId));

        // Update the book's details using data from the DTO
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setAuthor(bookDTO.getAuthor());
        existingBook.setIsbn(bookDTO.getIsbn());
        existingBook.setPublisher(bookDTO.getPublisher());
        existingBook.setPublicationDate(bookDTO.getPublicationDate());
        existingBook.setEdition(bookDTO.getEdition());
        existingBook.setGenre(bookDTO.getGenre());
        existingBook.setDescription(bookDTO.getDescription());
        existingBook.setLanguage(bookDTO.getLanguage());
        existingBook.setNumberOfPages(bookDTO.getNumberOfPages());
        existingBook.setCost(bookDTO.getCost());
        existingBook.setAvailable(bookDTO.isAvailable());

        // Save the updated book
        Book updatedBook = bookRepository.save(existingBook);

        // Return the updated book as a DTO
        return BookMapper.mapToBookDTO(updatedBook);
    }

    @Override
    public void deleteBook(int bookId) {
        if (bookId <= 0) {
            throw new CRUDAPIException(HttpStatus.BAD_REQUEST, "Invalid Book ID", "Book ID must be greater than 0.");
        }

        // Check if the book exists
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new CRUDAPIException(HttpStatus.NOT_FOUND, "Book Not Found", "No book found with ID: " + bookId));

        // Delete the book
        bookRepository.delete(existingBook);
    }
}


