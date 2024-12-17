package com.hexaware.librarymanagement.controller;

import com.hexaware.librarymanagement.dto.ReservedBookDTO;
import com.hexaware.librarymanagement.entity.Book;
import com.hexaware.librarymanagement.entity.User;
import com.hexaware.librarymanagement.exception.CRUDAPIException;
import com.hexaware.librarymanagement.service.IReservedBookService;
import com.hexaware.librarymanagement.repository.UserRepository;
import com.hexaware.librarymanagement.repository.BookRepository;
import com.hexaware.librarymanagement.repository.ReservedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reserved-books")
public class ReservedBookController {

    @Autowired
    private IReservedBookService reservedBookService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReservedBookRepository reservedBookRepository;

    @PostMapping("/reserve")
    public ResponseEntity<ReservedBookDTO> reserveBook(@RequestBody ReservedBookDTO reservedBookDTO) {
        // Validate User ID
        if (reservedBookDTO.getUserId() <= 0) {
            throw new CRUDAPIException(HttpStatus.BAD_REQUEST, "Invalid User data", "User ID is required.");
        }

        // Validate Book ID
        if (reservedBookDTO.getBookId() <= 0) {
            throw new CRUDAPIException(HttpStatus.BAD_REQUEST, "Invalid Book data", "Book ID is required.");
        }

        // Check if User exists
        Optional<User> userOptional = userRepository.findById(reservedBookDTO.getUserId());
        if (userOptional.isEmpty()) {
            throw new CRUDAPIException(HttpStatus.NOT_FOUND, "User not found", "No user found with ID: " + reservedBookDTO.getUserId());
        }

        // Check if Book exists
        Optional<Book> bookOptional = bookRepository.findById(reservedBookDTO.getBookId());
        if (bookOptional.isEmpty()) {
            throw new CRUDAPIException(HttpStatus.NOT_FOUND, "Book not found", "No book found with ID: " + reservedBookDTO.getBookId());
        }

        // Set User and Book for the ReservedBook
        reservedBookDTO.setUserId(userOptional.get().getId());
        reservedBookDTO.setBookId(bookOptional.get().getBookId());

        // Reserve the book using the service and return the result
        ReservedBookDTO savedReservedBook = reservedBookService.reserveBook(reservedBookDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedReservedBook);
    }

    @GetMapping
    public ResponseEntity<List<ReservedBookDTO>> getAllReservedBooks() {
        return ResponseEntity.ok(reservedBookService.getAllReservedBooks());
    }

    @GetMapping("/{reservedBookId}")
    public ResponseEntity<ReservedBookDTO> getReservedBookById(@PathVariable int reservedBookId) {
        ReservedBookDTO reservedBookDTO = reservedBookService.getReservedBookById(reservedBookId);
        if (reservedBookDTO == null) {
            throw new CRUDAPIException(HttpStatus.NOT_FOUND, "Reserved Book not found", "No reserved book found with ID: " + reservedBookId);
        }
        return ResponseEntity.ok(reservedBookDTO);
    }
}
