package com.hexaware.librarymanagement.service;

import com.hexaware.librarymanagement.dto.ReservedBookDTO;
import com.hexaware.librarymanagement.entity.Book;
import com.hexaware.librarymanagement.entity.ReservedBook;
import com.hexaware.librarymanagement.entity.User;
import com.hexaware.librarymanagement.exception.CRUDAPIException;
import com.hexaware.librarymanagement.mapper.ReservedBookMapper;
import com.hexaware.librarymanagement.repository.ReservedBookRepository;
import com.hexaware.librarymanagement.repository.UserRepository;
import com.hexaware.librarymanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservedBookServiceImpl implements IReservedBookService {

    @Autowired
    private ReservedBookRepository reservedBookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public ReservedBookDTO reserveBook(ReservedBookDTO reservedBookDTO) {
        // Validate User
        User user = userRepository.findById(reservedBookDTO.getUserId())
                .orElseThrow(() -> new CRUDAPIException(HttpStatus.NOT_FOUND, "User Not Found",
                        "User with ID " + reservedBookDTO.getUserId() + " does not exist."));

        // Validate Book
        Book book = bookRepository.findById(reservedBookDTO.getBookId())
                .orElseThrow(() -> new CRUDAPIException(HttpStatus.NOT_FOUND, "Book Not Found",
                        "Book with ID " + reservedBookDTO.getBookId() + " does not exist."));

        // Convert DTO to Entity
        ReservedBook reservedBook = new ReservedBook();
        reservedBook.setUser(user);
        reservedBook.setBook(book);
        reservedBook.setReservationDate(reservedBookDTO.getReservationDate() != null ?
                reservedBookDTO.getReservationDate() : new Date());

        // Save and return ReservedBookDTO
        ReservedBook savedReservedBook = reservedBookRepository.save(reservedBook);
        return ReservedBookMapper.mapToReservedBookDTO(savedReservedBook);
    }

    @Override
    public List<ReservedBookDTO> getReservedBooksByUser(int userId) {
        List<ReservedBook> reservedBooks = reservedBookRepository.findByUser_Id(userId);
        if (reservedBooks.isEmpty()) {
            throw new CRUDAPIException(HttpStatus.NOT_FOUND, "No Reserved Books Found",
                    "No reserved books found for user with ID " + userId);
        }

        // Map list of ReservedBook entities to DTOs
        return reservedBooks.stream()
                .map(ReservedBookMapper::mapToReservedBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservedBookDTO> getAllReservedBooks() {
        List<ReservedBook> reservedBooks = reservedBookRepository.findAll();
        return reservedBooks.stream()
                .map(ReservedBookMapper::mapToReservedBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReservedBookDTO getReservedBookById(int reservedBookId) {
        ReservedBook reservedBook = reservedBookRepository.findById(reservedBookId)
                .orElseThrow(() -> new CRUDAPIException(HttpStatus.NOT_FOUND, "Reserved Book Not Found",
                        "Reserved book with ID " + reservedBookId + " does not exist."));

        // Map ReservedBook entity to DTO
        return ReservedBookMapper.mapToReservedBookDTO(reservedBook);
    }
}