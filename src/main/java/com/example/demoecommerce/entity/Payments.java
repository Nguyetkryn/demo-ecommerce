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
@Table(name = "payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payments extends AbstractEntity {
    @Id
    private String paymentId;
    @Column(name = "description")
    @Lob
    private String description;
    @Column(name = "status")
    private String status;

    @ManyToMany
    @JoinTable(name = "user_payments",
            joinColumns = @JoinColumn(name = "paymentId"),
            inverseJoinColumns = @JoinColumn(name = "userId"))
    private List<Users> users = new ArrayList<>();
}
