package com.hexaware.librarymanagement.controller;

import com.hexaware.librarymanagement.dto.CategoryDTO;
import com.hexaware.librarymanagement.entity.Category;
import com.hexaware.librarymanagement.exception.CRUDAPIException;
import com.hexaware.librarymanagement.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
        if (categoryDTO == null || categoryDTO.getName() == null || categoryDTO.getName().isEmpty()) {
            throw new CRUDAPIException(HttpStatus.BAD_REQUEST, "Invalid Category data", "Category name is required.");
        }
        return ResponseEntity.ok(categoryService.addCategory(categoryDTO));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable int categoryId) {
        CategoryDTO categoryDTO = categoryService.getCategoryById(categoryId);
        if (categoryDTO == null) {
            throw new CRUDAPIException(HttpStatus.NOT_FOUND, "Category not found", "Category with ID " + categoryId + " not found.");
        }
        return ResponseEntity.ok(categoryDTO);
    }
}
