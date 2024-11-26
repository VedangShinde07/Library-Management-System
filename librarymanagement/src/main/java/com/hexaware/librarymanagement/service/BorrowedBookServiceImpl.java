package com.hexaware.librarymanagement.service;

import com.hexaware.librarymanagement.entity.BorrowedBook;
import com.hexaware.librarymanagement.repository.BorrowedBookRepository;
import com.hexaware.librarymanagement.service.BorrowedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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
        List<BorrowedBook> borrowedBooks = borrowedBookRepository.findAll();
        for (BorrowedBook borrowedBook : borrowedBooks) {
            calculateFine(borrowedBook);
        }
        return borrowedBooks;
    }

    @Override
    public BorrowedBook getBorrowedBookById(int borrowedBookId) {
        BorrowedBook borrowedBook = borrowedBookRepository.findById(borrowedBookId)
                .orElseThrow(() -> new RuntimeException("Borrowed Book not found with ID: " + borrowedBookId));
        calculateFine(borrowedBook);
        return borrowedBook;
    }

    private void calculateFine(BorrowedBook borrowedBook) {
        // Convert java.util.Date to java.time.LocalDate
        LocalDate dueDate = borrowedBook.getDueDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        LocalDate today = LocalDate.now();

        // Calculate the gap between the due date and today
        long borrowedToDueGap = ChronoUnit.DAYS.between(dueDate, today);

        if (borrowedToDueGap > 10) {
            double fine = (borrowedToDueGap - 10) * 5; // Rs 5 per day after 10 days
            borrowedBook.setFine(fine);
        } else {
            borrowedBook.setFine(0);
        }

        // Save the updated fine in the database
        borrowedBookRepository.save(borrowedBook);
    }

}