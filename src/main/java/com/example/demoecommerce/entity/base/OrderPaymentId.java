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
public class OrderPaymentId implements Serializable {
    private String orderId;
    private String paymentId;

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        OrderPaymentId that = (OrderPaymentId) o;
        return Objects.equals(orderId, that.orderId)
                && Objects.equals(paymentId, that.paymentId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(orderId,paymentId);
    }
}
