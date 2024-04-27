package com.example.demoecommerce.service.impl;

import com.example.demoecommerce.dto.CategoryDTO;
import com.example.demoecommerce.entity.Categories;
import com.example.demoecommerce.exception.ResourceNotFoundException;
import com.example.demoecommerce.mapper.CategoryMapper;
import com.example.demoecommerce.repository.ICategoryRepository;
import com.example.demoecommerce.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {
    private ICategoryRepository categoryRepository;
    CategoryMapper categoryMapper;
    @Override
    public CategoryDTO getCategoryById(String categoryId) {
        Categories category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category is not exists with given id: " + categoryId));
        return categoryMapper.categoriesToCategoryDTO(category);
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Categories categories = categoryMapper.categoryDTOToCategories(categoryDTO);
        return categoryMapper.categoriesToCategoryDTO(categories);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Categories> categoriesList = categoryRepository.findAllByAndActiveFlagIsTrueAndDeleteFlagIsFalse();
        return categoriesList.stream()
                .map(categoryMapper::categoriesToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO updateCategory(String categoryId, CategoryDTO updateCategory) {
        Categories categories = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category is not exist with given id " + categoryId));

        //Cập nhật thông tin danh mục từ DTO mới
        categories.setCategoryName(updateCategory.getCategoryName());
        //Lưu trạng thái cập nhật vào cơ sở dữ liệu
        categoryRepository.save(categories);
        return categoryMapper.categoriesToCategoryDTO(categories);
    }

    @Override
    public void deleteCategory(String categoryId) {
        Categories categories = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Catergory is not exist given id " + categoryId));
        categories.setActiveFlag(false);
        categories.setDeleteFlag(true);
        categoryRepository.save(categories);
    }
}
