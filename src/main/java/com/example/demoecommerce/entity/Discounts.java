package com.example.demoecommerce.entity;

import com.example.demoecommerce.entity.base.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "discounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Discounts extends AbstractEntity {
    @Id
    private String discountId;
    @Column(name = "discountName")
    private String discountName;
    @Column(name = "description")
    @Lob
    private String description;
    @Column(name = "discount")
    private Double discount;
    @Column(name = "startTime")
    private Date startTime;
    @Column(name = "endTime")
    private Date endTime;
    @ManyToMany(mappedBy = "discounts")
    private List<Products> products = new ArrayList<>();
}
