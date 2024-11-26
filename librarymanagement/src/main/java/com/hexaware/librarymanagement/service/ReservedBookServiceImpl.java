package com.hexaware.librarymanagement.service;

import com.hexaware.librarymanagement.entity.Book;
import com.hexaware.librarymanagement.entity.ReservedBook;
import com.hexaware.librarymanagement.entity.User;
import com.hexaware.librarymanagement.repository.ReservedBookRepository;
import com.hexaware.librarymanagement.repository.UserRepository;
import com.hexaware.librarymanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservedBookServiceImpl implements ReservedBookService {

    @Autowired
    private ReservedBookRepository reservedBookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public ReservedBook reserveBook(ReservedBook reservedBook) {
        User user = userRepository.findById(reservedBook.getUser().getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Fetch Book
        Book book = bookRepository.findById(reservedBook.getBook().getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        reservedBook.setUser(user);
        reservedBook.setBook(book);

        if (reservedBook.getReservationDate() == null) {
            reservedBook.setReservationDate(new Date());
        }

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