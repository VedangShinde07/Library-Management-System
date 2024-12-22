package com.hexaware.librarymanagement.repository;

import com.hexaware.librarymanagement.entity.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Integer> {
    List<BorrowedBook> findByUser_Id(int userId);
    List<BorrowedBook> findByBookBookId(int bookId);

    public Optional<BorrowedBook> findByUser_IdAndBook_BookId(int userId, int bookId);


}