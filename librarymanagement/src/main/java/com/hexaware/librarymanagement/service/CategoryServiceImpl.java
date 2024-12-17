package com.hexaware.librarymanagement.service;

import com.hexaware.librarymanagement.dto.CategoryDTO;
import com.hexaware.librarymanagement.entity.Category;
import com.hexaware.librarymanagement.exception.CRUDAPIException;
import com.hexaware.librarymanagement.mapper.CategoryMapper;
import com.hexaware.librarymanagement.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        // Convert DTO to entity
        Category category = CategoryMapper.mapToCategory(categoryDTO);

        // Save the entity to the database
        Category savedCategory = categoryRepository.save(category);

        // Convert saved entity back to DTO and return
        return CategoryMapper.mapToCategoryDTO(savedCategory);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        // Fetch all categories and convert them to DTOs
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(CategoryMapper::mapToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(int categoryId) {
        // Fetch category by ID and convert it to DTO
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CRUDAPIException(HttpStatus.NOT_FOUND, "Category Not Found", "Category with ID " + categoryId + " does not exist."));

        return CategoryMapper.mapToCategoryDTO(category);
    }
}