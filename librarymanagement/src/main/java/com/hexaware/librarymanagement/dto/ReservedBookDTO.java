package com.hexaware.librarymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservedBookDTO {
    private int reservationId;
    private int userId;
    private int bookId;
    private Date reservationDate;

}
