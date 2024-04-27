package com.example.demoecommerce.service;

import com.example.demoecommerce.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    CategoryDTO getCategoryById(String categoryId);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    List<CategoryDTO> getAllCategory();
    CategoryDTO updateCategory(String categoryId, CategoryDTO updateCategory);
    void deleteCategory (String categoryId);
}
