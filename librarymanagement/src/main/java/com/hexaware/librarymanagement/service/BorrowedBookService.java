package com.hexaware.librarymanagement.service;

import com.hexaware.librarymanagement.entity.BorrowedBook;

import java.util.List;

public interface BorrowedBookService {
    BorrowedBook borrowBook(BorrowedBook borrowedBook);
    List<BorrowedBook> getBorrowedBooksByUser(int userId);
    List<BorrowedBook> getAllBorrowedBooks(); // Add this method.

    BorrowedBook getBorrowedBookById(int borrowedBookId); // Add this method.
}