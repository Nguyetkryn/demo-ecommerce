package com.example.demoecommerce.dto;

import com.example.demoecommerce.dto.baseDto.AbstractDTO;
import com.example.demoecommerce.entity.ProductImages;
import lombok.*;

import java.util.List;

@Data
public class ProductDTO extends AbstractDTO {
    private String productId;
    private String productName;
    private String description;
    private Double price;
    private String size;
    private String brand;
    private String amount;
    private String category;
    private List<ProductImageDTO> productImages;
    private List<String> discounts;
}
