package com.hexaware.librarymanagement.service;

import com.hexaware.librarymanagement.entity.BorrowedBook;
import com.hexaware.librarymanagement.repository.BorrowedBookRepository;
import com.hexaware.librarymanagement.service.BorrowedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowedBookServiceImpl implements BorrowedBookService {

    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    @Override
    public BorrowedBook borrowBook(BorrowedBook borrowedBook) {
        return borrowedBookRepository.save(borrowedBook);
    }

    @Override
    public List<BorrowedBook> getBorrowedBooksByUser(int userId) {
        return borrowedBookRepository.findByUserUserId(userId);
    }

    @Override
    public List<BorrowedBook> getAllBorrowedBooks() {
        return borrowedBookRepository.findAll(); // Uses JpaRepository's built-in findAll() method.
    }

    @Override
    public BorrowedBook getBorrowedBookById(int borrowedBookId) {
        return borrowedBookRepository.findById(borrowedBookId)
                .orElseThrow(() -> new RuntimeException("Borrowed Book not found with ID: " + borrowedBookId));
    }
}