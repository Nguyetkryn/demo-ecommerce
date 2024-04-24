package com.example.demoecommerce.entity;

import com.example.demoecommerce.entity.base.AbstractEntity;
import com.example.demoecommerce.entity.base.OrderDetailId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_detail")
@IdClass(OrderDetailId.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail extends AbstractEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId", nullable = false)
    private Orders orderId;
    @Id
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId", nullable = false)
    private Products productId;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price")
    private Double price;
}
