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
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Products extends AbstractEntity {
    @Id
    private String productId;
    @Column(name = "productName")
    private String productName;
    @Column(name = "description")
    @Lob
    private String description;
    @Column(name = "price")
    private Double price;
    @Column(name = "size")
    private String size;
    @Column(name = "brand")
    private String brand;
    @Column(name = "amount")
    private String amount; //số luợng (amount) viết nhầm thành trạng thái (status)

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Categories category;

    @OneToMany(mappedBy = "product")
    private List<ProductImages> productImages = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "has_discount",
                joinColumns = @JoinColumn(name = "productId"),
                inverseJoinColumns = @JoinColumn(name = "discountId"))
    private List<Discounts> discounts = new ArrayList<>();
}
