package com.example.demoecommerce.repository;

import com.example.demoecommerce.entity.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductImageRepository extends JpaRepository<ProductImages, String> {
}
