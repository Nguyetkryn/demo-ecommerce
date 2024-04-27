package com.example.demoecommerce.mapper;

import com.example.demoecommerce.dto.CategoryDTO;
import com.example.demoecommerce.entity.Categories;
import com.example.demoecommerce.entity.Products;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface CategoryMapper {

    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "categoryName", target = "categoryName")
    @Mapping(source = "products", target = "products")
    CategoryDTO categoriesToCategoryDTO (Categories categories);

    List<CategoryDTO> categoriesListToCategoryDTOList (List<Categories> categoriesList);

    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "categoryName", target = "categoryName")
    @Mapping(source = "products", target = "products")
    Categories categoryDTOToCategories (CategoryDTO categoryDTO);

    List<Categories> categoryDTOListToCategoriesList (List<CategoryDTO> categoryDTOList);
}
