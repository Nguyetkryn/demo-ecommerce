package com.example.demoecommerce.entity;

import com.example.demoecommerce.entity.base.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoices extends AbstractEntity {
    @Id
    private String invoiceId;
    @OneToOne
    @JoinColumn(name = "orderId")
    private Orders order;
    @Column(name = "time")
    private Date time;

}
