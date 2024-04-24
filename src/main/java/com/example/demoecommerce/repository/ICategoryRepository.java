package com.example.demoecommerce.repository;

import com.example.demoecommerce.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<Categories, String> {
    Optional<Categories> findById(String categoryId);
}
