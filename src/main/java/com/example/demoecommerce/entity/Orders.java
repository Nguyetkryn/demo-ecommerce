package com.example.demoecommerce.entity;

import com.example.demoecommerce.entity.base.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders extends AbstractEntity {
    @Id
    private String orderId;
    @Column(name = "total")
    private Double total;
    @Column(name = "orderDate")
    private Double orderDate;
    @Column(name = "address")
    private String address;
    @Column(name = "phoneNumber")
    @Pattern(regexp = "^\\d{10}$",message = "Invalid phone number")
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
    private String phoneNumber;
    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;

    @OneToOne(mappedBy = "order")
    private Invoices invoice;
}
