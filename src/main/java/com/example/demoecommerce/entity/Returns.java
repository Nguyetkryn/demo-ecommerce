package com.example.demoecommerce.entity;

import com.example.demoecommerce.entity.base.AbstractEntity;
import com.example.demoecommerce.entity.base.ReturnsId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "returns")
@IdClass(ReturnsId.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Returns extends AbstractEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "orderId")
    private Orders orderId;
    @Id
    @ManyToOne
    @JoinColumn(name = "productId")
    private Products productId;

    @Column(name = "reason")
    @Lob
    private String reason;
    @Column(name = "status")
    private String status;
    @Column(name = "returnDate")
    private Date returnDate;
}
