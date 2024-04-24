package com.example.demoecommerce.service.impl;

import com.example.demoecommerce.dto.CategoryDTO;
import com.example.demoecommerce.entity.Categories;
import com.example.demoecommerce.exception.ResourceNotFoundException;
import com.example.demoecommerce.mapper.CategoryMapper;
import com.example.demoecommerce.repository.ICategoryRepository;
import com.example.demoecommerce.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {
    private ICategoryRepository categoryRepository;
    CategoryMapper categoryMapper;
    @Override
    public CategoryDTO getCategoryById(String categoryId) {
        Categories category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product is not exists with given id: " + categoryId));
        return categoryMapper.toCategoryDTO(category);
    }
}
