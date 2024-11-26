package com.hexaware.librarymanagement.controller;

import com.hexaware.librarymanagement.dto.ReservedBookDTO;
import com.hexaware.librarymanagement.entity.Book;
import com.hexaware.librarymanagement.entity.ReservedBook;
import com.hexaware.librarymanagement.entity.User;
import com.hexaware.librarymanagement.service.ReservedBookService;
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
    private ReservedBookService reservedBookService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReservedBookRepository reservedBookRepository;

    @PostMapping("/reserve")
    public ResponseEntity<ReservedBook> reserveBook(@RequestBody ReservedBook reservedBook) {

        Optional<User> userOptional = userRepository.findById(reservedBook.getUser().getUserId());
        Optional<Book> bookOptional = bookRepository.findById(reservedBook.getBook().getBookId());

        // Validate that both user and book exist
        if (userOptional.isEmpty() || bookOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        // Set the complete user and book objects in the reservedBook entity
        reservedBook.setUser(userOptional.get());
        reservedBook.setBook(bookOptional.get());

        // Save the reserved book in the database
        ReservedBook savedReservedBook = reservedBookRepository.save(reservedBook);

        // Return the saved ReservedBook entity as a response
        return ResponseEntity.ok(savedReservedBook);
    }

    @GetMapping
    public ResponseEntity<List<ReservedBook>> getAllReservedBooks() {
        return ResponseEntity.ok(reservedBookService.getAllReservedBooks());
    }

    @GetMapping("/{reservedBookId}")
    public ResponseEntity<ReservedBook> getReservedBookById(@PathVariable int reservedBookId) {
        return ResponseEntity.ok(reservedBookService.getReservedBookById(reservedBookId));
    }
}
