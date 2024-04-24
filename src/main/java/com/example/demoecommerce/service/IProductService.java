package com.example.demoecommerce.service;

import com.example.demoecommerce.dto.ProductDTO;
import com.example.demoecommerce.dto.ProductImageDTO;
import com.example.demoecommerce.entity.Products;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService {

    ProductDTO creatProduct(ProductDTO productDTO, MultipartFile[] images);
    ProductDTO getProductId(String productId);
    List<ProductDTO> getAllProducts();
    ProductDTO updateProduct(String productId, ProductDTO updateProduct);
    void deleteProduct(String productId);

}
