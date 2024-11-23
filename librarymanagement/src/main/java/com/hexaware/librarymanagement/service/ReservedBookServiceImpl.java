package com.hexaware.librarymanagement.service;

import com.hexaware.librarymanagement.entity.ReservedBook;
import com.hexaware.librarymanagement.repository.ReservedBookRepository;
import com.hexaware.librarymanagement.service.ReservedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservedBookServiceImpl implements ReservedBookService {

    @Autowired
    private ReservedBookRepository reservedBookRepository;

    @Override
    public ReservedBook reserveBook(ReservedBook reservedBook) {
        return reservedBookRepository.save(reservedBook);
    }

    @Override
    public List<ReservedBook> getReservedBooksByUser(int userId) {
        return reservedBookRepository.findByUserUserId(userId);
    }
    @Override
    public List<ReservedBook> getAllReservedBooks() {
        return reservedBookRepository.findAll(); // Fetch all reserved books.
    }

    @Override
    public ReservedBook getReservedBookById(int reservedBookId) {
        return reservedBookRepository.findById(reservedBookId)
                .orElseThrow(() -> new RuntimeException("Reserved Book not found with ID: " + reservedBookId));
    }
}