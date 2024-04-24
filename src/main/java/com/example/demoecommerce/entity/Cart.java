package com.example.demoecommerce.entity;

import com.example.demoecommerce.entity.base.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends AbstractEntity {
    @Id
    private String cartId;
    @Column(name = "status")
    private String status;
    @OneToOne(mappedBy = "cart")
    private Users user;
}
