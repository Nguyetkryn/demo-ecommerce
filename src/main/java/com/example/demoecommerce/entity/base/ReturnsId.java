package com.example.demoecommerce.entity.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReturnsId implements Serializable {
    private String orderId;
    private String productId;

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ReturnsId that = (ReturnsId) o;
        return Objects.equals(orderId, that.orderId)
                && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(orderId,productId);
    }
}
