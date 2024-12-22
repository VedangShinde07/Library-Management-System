package com.hexaware.librarymanagement.service;

import com.hexaware.librarymanagement.dto.BorrowedBookDTO;
import com.hexaware.librarymanagement.entity.BorrowedBook;
import com.hexaware.librarymanagement.exception.CRUDAPIException;
import com.hexaware.librarymanagement.mapper.BorrowedBookMapper;
import com.hexaware.librarymanagement.repository.BorrowedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowedBookServiceImpl implements IBorrowedBookService {

    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    @Override
    public BorrowedBookDTO borrowBook(BorrowedBookDTO borrowedBookDTO) {
        if (borrowedBookDTO.getDueDate() == null) {
            throw new CRUDAPIException(HttpStatus.BAD_REQUEST, "Validation Error", "Due date is required for borrowing a book.");
        }

        BorrowedBook borrowedBook = BorrowedBookMapper.mapToBorrowedBook(borrowedBookDTO);
        BorrowedBook savedBorrowedBook = borrowedBookRepository.save(borrowedBook);
        return BorrowedBookMapper.mapToBorrowedBookDTO(savedBorrowedBook);
    }

    @Override
    public List<BorrowedBookDTO> getBorrowedBooksByUser(int userId) {
        List<BorrowedBook> borrowedBooks = borrowedBookRepository.findByUser_Id(userId);
        if (borrowedBooks.isEmpty()) {
            throw new CRUDAPIException(HttpStatus.NOT_FOUND, "No Records Found", "No borrowed books found for User ID: " + userId);
        }
        return borrowedBooks.stream()
                .peek(this::calculateFine)
                .map(BorrowedBookMapper::mapToBorrowedBookDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<BorrowedBookDTO> getAllBorrowedBooks() {
        List<BorrowedBook> borrowedBooks = borrowedBookRepository.findAll();
        return borrowedBooks.stream()
                .peek(this::calculateFine)
                .map(BorrowedBookMapper::mapToBorrowedBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public double returnBook(int borrowId) {
        // Find the borrowed record based on borrowId
        BorrowedBook borrowedBook = borrowedBookRepository.findById(borrowId)
                .orElseThrow(() -> new CRUDAPIException(HttpStatus.NOT_FOUND,
                        "Borrowed record not found for Borrow ID: " + borrowId));

        // Set the return date to the current date
        borrowedBook.setReturnDate(new Date());

        // Calculate the fine based on the return date
        double fine = calculateFine(borrowedBook);

        // Set the calculated fine
        borrowedBook.setFine(fine);

        // Save the updated record
        borrowedBookRepository.save(borrowedBook);

        // Now delete the record as per your original requirement
        borrowedBookRepository.delete(borrowedBook);

        // Return the calculated fine
        return fine;
    }



    @Override
    public BorrowedBookDTO getBorrowedBookById(int borrowedBookId) {
        BorrowedBook borrowedBook = borrowedBookRepository.findById(borrowedBookId)
                .orElseThrow(() -> new CRUDAPIException(HttpStatus.NOT_FOUND, "Record Not Found", "Borrowed Book not found with ID: " + borrowedBookId));
        calculateFine(borrowedBook);
        return BorrowedBookMapper.mapToBorrowedBookDTO(borrowedBook);
    }

    public double calculateFine(BorrowedBook borrowedBook) {
        // Validate that the due date is present
        if (borrowedBook.getDueDate() == null) {
            throw new CRUDAPIException(
                    HttpStatus.BAD_REQUEST,
                    "Validation Error",
                    "Due date is missing for Borrowed Book ID: " + borrowedBook.getBorrowId()
            );
        }

        // Convert Date to LocalDate
        LocalDate dueDate = borrowedBook.getDueDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate returnDate = LocalDate.now();

        // Calculate the days overdue
        long overdueDays = ChronoUnit.DAYS.between(dueDate, returnDate);

        // Fine logic: Rs. 5 per day for overdue days (only if overdueDays > 0)
        double fine = overdueDays > 0 ? overdueDays * 5 : 0;

        // Return the calculated fine
        return fine;
    }


}