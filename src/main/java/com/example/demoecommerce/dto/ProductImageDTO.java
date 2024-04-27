package com.example.demoecommerce.dto;

import com.example.demoecommerce.dto.baseDto.AbstractDTO;
import lombok.*;

@Data
public class ProductImageDTO extends AbstractDTO {
    private Long imageId;
    private String product;
    private String imageUrl;
}
