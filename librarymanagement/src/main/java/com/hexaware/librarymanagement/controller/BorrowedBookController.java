package com.hexaware.librarymanagement.controller;

import com.hexaware.librarymanagement.entity.Book;
import com.hexaware.librarymanagement.entity.BorrowedBook;
import com.hexaware.librarymanagement.entity.User;
import com.hexaware.librarymanagement.repository.UserRepository;
import com.hexaware.librarymanagement.repository.BorrowedBookRepository;
import com.hexaware.librarymanagement.repository.BookRepository;
import com.hexaware.librarymanagement.service.BorrowedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/borrowed-books")
public class BorrowedBookController {

    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    @Autowired
    private BorrowedBookService borrowedBookService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/borrow")
    public ResponseEntity<BorrowedBook> borrowBook(@RequestBody BorrowedBook borrowedBook) {
        Optional<User> userOptional = userRepository.findById(borrowedBook.getUser().getUserId());
        Optional<Book> bookOptional = bookRepository.findById(borrowedBook.getBook().getBookId());

        // Validate that both user and book exist
        if (userOptional.isEmpty() || bookOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        // Set the complete user and book objects in the borrowedBook entity
        borrowedBook.setUser(userOptional.get());
        borrowedBook.setBook(bookOptional.get());

        // Set the borrowed date if not already provided
        if (borrowedBook.getBorrowedDate() == null) {
            borrowedBook.setBorrowedDate(new Date());
        }

        // Set a default fine of 0.0 (can be updated later if necessary)
        if (borrowedBook.getFine() == 0.0) {
            borrowedBook.setFine(0.0);
        }

        // Save the borrowed book in the database using the injected repository
        BorrowedBook savedBorrowedBook = borrowedBookRepository.save(borrowedBook);

        // Return the saved BorrowedBook entity as a response
        return ResponseEntity.ok(savedBorrowedBook);
    }

    @GetMapping
    public ResponseEntity<List<BorrowedBook>> getAllBorrowedBooks() {
        return ResponseEntity.ok(borrowedBookService.getAllBorrowedBooks());
    }

    @GetMapping("/{borrowedBookId}")
    public ResponseEntity<BorrowedBook> getBorrowedBookById(@PathVariable int borrowedBookId) {
        return ResponseEntity.ok(borrowedBookService.getBorrowedBookById(borrowedBookId));
    }
}
