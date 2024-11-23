package com.hexaware.librarymanagement.service;

import com.hexaware.librarymanagement.entity.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(Category category);
    List<Category> getAllCategories();
    Category getCategoryById(int categoryId);
}