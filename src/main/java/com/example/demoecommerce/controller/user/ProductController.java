package com.example.demoecommerce.controller.user;

import com.example.demoecommerce.dto.ProductDTO;
import com.example.demoecommerce.entity.Products;
import com.example.demoecommerce.service.IProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/no-auth/products")
@AllArgsConstructor
public class ProductController {

    private IProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProduct(){
        List<ProductDTO> productDTOs = productService.getAllProducts();
        return ResponseEntity.ok(productDTOs);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") String productId){
        ProductDTO getProduct = productService.getProductId(productId);
        return ResponseEntity.ok(getProduct);
    }
}
