package com.example.demoecommerce.entity;

import com.example.demoecommerce.entity.base.AbstractEntity;
import com.example.demoecommerce.entity.base.CartItemId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cart_items")
@IdClass(CartItemId.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItems extends AbstractEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "cartId", referencedColumnName = "cartId", nullable = false)
    private Cart cartId;
    @Id
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId", nullable = false)
    private Products productId;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price")
    private Double price;
}
