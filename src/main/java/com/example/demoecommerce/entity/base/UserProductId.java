package com.example.demoecommerce.entity.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProductId implements Serializable {
    private String userId;
    private String productId;

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserProductId that = (UserProductId) o;
        return Objects.equals(userId, that.userId)
                && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(userId,productId);
    }
}
