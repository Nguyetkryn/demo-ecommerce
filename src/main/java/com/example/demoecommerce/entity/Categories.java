package com.example.demoecommerce.entity;

import com.example.demoecommerce.entity.base.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categories extends AbstractEntity {
    @Id
    private String categoryId;
    @Column(name = "categoryName")
    private String categoryName;
    @OneToMany(mappedBy = "category")
    private List<Products> products = new ArrayList<>();
}
