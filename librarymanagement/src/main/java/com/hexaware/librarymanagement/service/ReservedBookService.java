package com.hexaware.librarymanagement.service;

import com.hexaware.librarymanagement.entity.ReservedBook;

import java.util.List;

public interface ReservedBookService {
    ReservedBook reserveBook(ReservedBook reservedBook);
    List<ReservedBook> getReservedBooksByUser(int userId);
    List<ReservedBook> getAllReservedBooks(); // Add this method.

    ReservedBook getReservedBookById(int reservedBookId); // Add this method.
}