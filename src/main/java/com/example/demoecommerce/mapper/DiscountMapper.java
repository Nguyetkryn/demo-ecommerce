package com.example.demoecommerce.mapper;

import com.example.demoecommerce.dto.DiscountDTO;
import com.example.demoecommerce.entity.Discounts;
import com.example.demoecommerce.entity.Products;

import java.util.ArrayList;
import java.util.List;

public class DiscountMapper {
    public DiscountDTO toDiscountDTO(Discounts discounts){
        DiscountDTO discountDTO = new DiscountDTO();

        discountDTO.setDiscountId(discounts.getDiscountId());
        discountDTO.setDiscountName(discounts.getDiscountName());
        discountDTO.setDescription(discounts.getDescription());
        discountDTO.setDiscount(discounts.getDiscount());
        discountDTO.setStartTime(discounts.getStartTime());
        discountDTO.setEndTime(discounts.getEndTime());

        List<String> productIds = new ArrayList<>();
        for (Products products : discounts.getProducts()){
            productIds.add(products.getProductId());
        }
        discountDTO.setProducts(productIds);

        return discountDTO;
    }

    public Discounts toDiscount(DiscountDTO discountDTO){
        Discounts discount = new Discounts();

        discount.setDiscountId(discountDTO.getDiscountId());
        discount.setDiscountName(discountDTO.getDiscountName());
        discount.setDescription(discountDTO.getDescription());
        discount.setDiscount(discountDTO.getDiscount());
        discount.setStartTime(discountDTO.getStartTime());
        discount.setEndTime(discountDTO.getEndTime());

        List<Products> products = new ArrayList<>();
        for (String product : discountDTO.getProducts()) {
            Products productCode = new Products();
            productCode.setProductId(product);
            products.add(productCode);
        }

        return discount;
    }

    public List<DiscountDTO> toDiscountDTOList (List<Discounts> discounts){
        List<DiscountDTO> discountDTOs = new ArrayList<>();
        for (Discounts discount : discounts){
            discountDTOs.add(toDiscountDTO(discount));
        }
        return discountDTOs;
    }

    public List<Discounts> toDiscountList (List<DiscountDTO> discountDTOs){
        List<Discounts> discounts = new ArrayList<>();
        for (DiscountDTO discountDTO : discountDTOs){
            discounts.add(toDiscount(discountDTO));
        }
        return discounts;
    }
}
