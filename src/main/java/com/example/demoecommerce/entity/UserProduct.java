package com.example.demoecommerce.entity;

import com.example.demoecommerce.entity.base.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "user_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProduct extends AbstractEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "userId")
    private Users userId;
    @Id
    @ManyToOne
    @JoinColumn(name = "productId")
    private Products productId;

    @Column(name = "viewDate")
    private Date viewDate;
    @Column(name = "purchaseDate")
    private Date purchaseDate;
}
