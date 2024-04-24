package com.example.demoecommerce.repository;

import com.example.demoecommerce.entity.Categories;
import com.example.demoecommerce.entity.Discounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDiscountRepository extends JpaRepository<Discounts, String> {
}
