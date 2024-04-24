package com.example.demoecommerce.controller.admin;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demoecommerce.config.CloudinaryConfig;
import com.example.demoecommerce.dto.ProductDTO;
import com.example.demoecommerce.dto.ProductImageDTO;
import com.example.demoecommerce.entity.ProductImages;
import com.example.demoecommerce.repository.IProductRepository;
import com.example.demoecommerce.service.IProductImageService;
import com.example.demoecommerce.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/products")
@AllArgsConstructor
public class AdminProductController {

    private IProductService productService;
    @Autowired
    private CloudinaryConfig cloudinary;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<ProductDTO> createProduct(@ModelAttribute ProductDTO productDTO, @RequestParam("images") MultipartFile[] productImages) throws IOException {
        StringBuilder urls = new StringBuilder();
        ProductDTO saveProduct = new ProductDTO();
        saveProduct = productService.creatProduct(productDTO, productImages);
        return new ResponseEntity<>(saveProduct, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") String productId){
        ProductDTO getProduct = productService.getProductId(productId);
        return ResponseEntity.ok(getProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProduct(){
        List<ProductDTO> productDTOs = productService.getAllProducts();
        return ResponseEntity.ok(productDTOs);
    }
    @PutMapping("{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") String productId,
                                                    @RequestBody ProductDTO updateProduct){
        ProductDTO productDTO = productService.updateProduct(productId, updateProduct);
        return ResponseEntity.ok(productDTO);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(String productId){
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted successfully!");
    }
}
