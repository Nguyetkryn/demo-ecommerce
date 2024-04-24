package com.example.demoecommerce.entity.base;

import com.example.demoecommerce.entity.CartItems;
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
public class CartItemId implements Serializable {
    private String cartId;
    private String productId;

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CartItemId that = (CartItemId) o;
        return Objects.equals(cartId, that.cartId)
                && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode(){
        return  Objects.hash(cartId, productId);
    }
}
