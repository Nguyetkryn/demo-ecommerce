package com.example.demoecommerce.mapper;

import com.example.demoecommerce.dto.ProductImageDTO;
import com.example.demoecommerce.entity.ProductImages;
import com.example.demoecommerce.entity.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductImageMapper {

    public ProductImageDTO toProductImageDTO(ProductImages productImages){
        ProductImageDTO productImageDTO = new ProductImageDTO();

        productImageDTO.setImageId(productImages.getImageId());
        productImageDTO.setProduct(productImages.getProduct().getProductId());
        productImageDTO.setImageUrl(productImages.getImageUrl());

        return productImageDTO;
    }

    public ProductImages toProductImage(ProductImageDTO productImageDTO){
        ProductImages image = new ProductImages();

        image.setImageId(productImageDTO.getImageId());

        Products product = new Products();
        product.setProductId(productImageDTO.getProduct());
        image.setProduct(product);

        image.setImageUrl(productImageDTO.getImageUrl());

        return image;
    }

    public List<ProductImageDTO> toProductImageDTOList(List<ProductImages> productImages){
        List<ProductImageDTO> imageDTOs = new ArrayList<>();
        for (ProductImages image : productImages){
            imageDTOs.add(toProductImageDTO(image));
        }
        return imageDTOs;
    }

    public List<ProductImages> toProductImageList(List<ProductImageDTO> productImageDTOs){
        List<ProductImages> images = new ArrayList<>();
        for (ProductImageDTO imageDTO : productImageDTOs){
            images.add(toProductImage(imageDTO));
        }
        return images;
    }
}
