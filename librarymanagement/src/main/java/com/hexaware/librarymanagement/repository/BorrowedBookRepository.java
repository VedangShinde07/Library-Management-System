package com.hexaware.librarymanagement.repository;

import com.hexaware.librarymanagement.entity.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Integer> {
    List<BorrowedBook> findByUserUserId(int userId);
    List<BorrowedBook> findByBookBookId(int bookId);
}