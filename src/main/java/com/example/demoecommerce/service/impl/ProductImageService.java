package com.example.demoecommerce.service.impl;

import com.example.demoecommerce.dto.ProductDTO;
import com.example.demoecommerce.dto.ProductImageDTO;
import com.example.demoecommerce.entity.ProductImages;
import com.example.demoecommerce.mapper.ProductImageMapper;
import com.example.demoecommerce.repository.IProductImageRepository;
import com.example.demoecommerce.service.IProductImageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductImageService implements IProductImageService {

    @Autowired
    private IProductImageRepository productImageRepository;

    @Override
    public void addProductImage(ProductImageDTO productImageDTO) {
        ProductImageMapper productImageMapper = new ProductImageMapper();
        ProductImages productImages = productImageMapper.toProductImage(productImageDTO);
        productImageRepository.save(productImages);
    }
}
