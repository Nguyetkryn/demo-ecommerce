package com.example.demoecommerce.mapper;

import com.example.demoecommerce.dto.CategoryDTO;
import com.example.demoecommerce.entity.Categories;
import com.example.demoecommerce.entity.Products;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class CategoryMapper {
    public CategoryDTO toCategoryDTO(Categories categories){
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setCategoryId(categories.getCategoryId());
        categoryDTO.setCategoryName(categories.getCategoryName());

        List<String> productIds = new ArrayList<>();
        for (Products product : categories.getProducts()){
            productIds.add(product.getProductId());
        }
        categoryDTO.setProducts(productIds);

        return categoryDTO;
    }

    public Categories toCategory(CategoryDTO categoryDTO){
        Categories category = new Categories();

        category.setCategoryId(categoryDTO.getCategoryId());
        category.setCategoryName(categoryDTO.getCategoryName());

        List<Products> products = new ArrayList<>();
        for (String product : categoryDTO.getProducts()){
            Products productCode = new Products();
            productCode.setProductId(product);
            products.add(productCode);
        }

        return category;
    }

    public List<CategoryDTO> toCategoryDTOList(List<Categories> categories){
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        for (Categories category : categories){
            categoryDTOs.add(toCategoryDTO(category));
        }
        return categoryDTOs;
    }

    public List<Categories> toCategoryList(List<CategoryDTO> categoryDTOs){
        List<Categories> categories = new ArrayList<>();
        for (CategoryDTO categoryDTO : categoryDTOs){
            categories.add(toCategory(categoryDTO));
        }
        return categories;
    }
}
