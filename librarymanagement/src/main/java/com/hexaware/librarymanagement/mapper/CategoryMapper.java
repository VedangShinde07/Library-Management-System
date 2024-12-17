package com.hexaware.librarymanagement.mapper;

import com.hexaware.librarymanagement.dto.CategoryDTO;
import com.hexaware.librarymanagement.entity.Category;

public class CategoryMapper {

    // Method to map CategoryDTO to Category entity
    public static Category mapToCategory(CategoryDTO dto) {
        Category category = new Category();

        category.setCategoryId(dto.getCategoryId());
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        return category;
    }

    // Method to map Category entity to CategoryDTO
    public static CategoryDTO mapToCategoryDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();

        dto.setCategoryId(category.getCategoryId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());

        return dto;
    }
}
