package com.hexaware.librarymanagement.repository;

import com.hexaware.librarymanagement.entity.ReservedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservedBookRepository extends JpaRepository<ReservedBook, Integer> {
    List<ReservedBook> findByUser_Id(int userId);
    List<ReservedBook> findByBookBookId(int bookId);
}