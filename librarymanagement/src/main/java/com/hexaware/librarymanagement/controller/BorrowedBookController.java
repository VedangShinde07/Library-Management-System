package com.hexaware.librarymanagement.controller;

import com.hexaware.librarymanagement.entity.BorrowedBook;
import com.hexaware.librarymanagement.service.BorrowedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowed-books")
public class BorrowedBookController {

    @Autowired
    private BorrowedBookService borrowedBookService;

    @PostMapping("/borrow")
    public ResponseEntity<BorrowedBook> borrowBook(@RequestBody BorrowedBook borrowedBook) {
        return ResponseEntity.ok(borrowedBookService.borrowBook(borrowedBook));
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
