package com.hexaware.librarymanagement.controller;

import com.hexaware.librarymanagement.entity.ReservedBook;
import com.hexaware.librarymanagement.service.ReservedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserved-books")
public class ReservedBookController {

    @Autowired
    private ReservedBookService reservedBookService;

    @PostMapping("/reserve")
    public ResponseEntity<ReservedBook> reserveBook(@RequestBody ReservedBook reservedBook) {
        return ResponseEntity.ok(reservedBookService.reserveBook(reservedBook));
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
