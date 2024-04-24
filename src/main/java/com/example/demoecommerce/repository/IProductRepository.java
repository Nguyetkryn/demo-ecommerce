package com.example.demoecommerce.repository;

import com.example.demoecommerce.entity.Products;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<Products, String> {
    List<Products> findAllByAndActiveFlagIsTrueAndDeleteFlagIsFalse();
    Optional<Products> findByProductIdAndActiveFlagIsTrueAndDeleteFlagIsFalse(String id);
}
