package com.hexaware.librarymanagement.service;

import com.hexaware.librarymanagement.dto.ReservedBookDTO;

import java.util.List;

public interface IReservedBookService {
    ReservedBookDTO reserveBook(ReservedBookDTO reservedBookDTO);  // Accept and return ReservedBookDTO for abstraction
    List<ReservedBookDTO> getReservedBooksByUser(int userId);      // Return a list of ReservedBookDTOs
    List<ReservedBookDTO> getAllReservedBooks();                   // Return a list of ReservedBookDTOs
    ReservedBookDTO getReservedBookById(int reservedBookId);       // Return a ReservedBookDTO for a specific ID
}
