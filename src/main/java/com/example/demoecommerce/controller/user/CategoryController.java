package com.example.demoecommerce.controller.user;

import com.example.demoecommerce.dto.CategoryDTO;
import com.example.demoecommerce.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/categories")
@AllArgsConstructor
public class CategoryController {
    private ICategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory (@ModelAttribute CategoryDTO categoryDTO) {
        CategoryDTO saveCategory = new CategoryDTO();
        saveCategory = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(saveCategory, HttpStatus.CREATED);
    }
}
