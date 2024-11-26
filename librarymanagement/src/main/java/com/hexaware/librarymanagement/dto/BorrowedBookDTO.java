package com.hexaware.librarymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowedBookDTO {
    private int borrowId;
    private int userId;
    private int bookId;
    private Date borrowedDate;
    private Date dueDate;
    private Date returnDate;
    private double fine;
}
