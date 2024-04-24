package com.example.demoecommerce.service;

import com.example.demoecommerce.dto.CategoryDTO;

public interface ICategoryService {
    CategoryDTO getCategoryById(String categoryId);
}
