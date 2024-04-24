package com.example.demoecommerce.entity;

import com.example.demoecommerce.entity.base.AbstractEntity;
import com.example.demoecommerce.entity.base.OrderPaymentId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_payment")
@IdClass(OrderPaymentId.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderPayment extends AbstractEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "orderId")
    private Orders orderId;
    @Id
    @ManyToOne
    @JoinColumn(name = "paymentId")
    private Payments paymentId;

    @Column(name = "amount")
    private Double amount;
}
