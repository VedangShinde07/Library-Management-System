package com.hexaware.librarymanagement.mapper;

import com.hexaware.librarymanagement.dto.ReservedBookDTO;
import com.hexaware.librarymanagement.entity.ReservedBook;
import com.hexaware.librarymanagement.entity.User;
import com.hexaware.librarymanagement.entity.Book;

public class ReservedBookMapper {

    // Method to map ReservedBookDTO to ReservedBook entity
    public static ReservedBook mapToReservedBook(ReservedBookDTO dto) {
        ReservedBook reservedBook = new ReservedBook();

        reservedBook.setReservationId(dto.getReservationId());

        // Map userId to User entity
        User user = new User();
        user.setId(dto.getUserId());
        reservedBook.setUser(user);

        // Map bookId to Book entity
        Book book = new Book();
        book.setBookId(dto.getBookId());
        reservedBook.setBook(book);

        reservedBook.setReservationDate(dto.getReservationDate());

        return reservedBook;
    }

    // Method to map ReservedBook entity to ReservedBookDTO
    public static ReservedBookDTO mapToReservedBookDTO(ReservedBook reservedBook) {
        ReservedBookDTO dto = new ReservedBookDTO();

        dto.setReservationId(reservedBook.getReservationId());
        dto.setUserId(reservedBook.getUser().getId());
        dto.setBookId(reservedBook.getBook().getBookId());
        dto.setReservationDate(reservedBook.getReservationDate());

        return dto;
    }
}
