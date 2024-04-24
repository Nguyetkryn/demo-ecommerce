package com.example.demoecommerce.service.impl;

import com.cloudinary.utils.ObjectUtils;
import com.example.demoecommerce.config.CloudinaryConfig;
import com.example.demoecommerce.dto.ProductDTO;
import com.example.demoecommerce.dto.ProductImageDTO;
import com.example.demoecommerce.entity.ProductImages;
import com.example.demoecommerce.entity.Products;
import com.example.demoecommerce.exception.ResourceNotFoundException;
import com.example.demoecommerce.mapper.ProductImageMapper;
import com.example.demoecommerce.mapper.ProductMapper;
import com.example.demoecommerce.repository.ICategoryRepository;
import com.example.demoecommerce.repository.IDiscountRepository;
import com.example.demoecommerce.repository.IProductImageRepository;
import com.example.demoecommerce.repository.IProductRepository;
import com.example.demoecommerce.service.IProductImageService;
import com.example.demoecommerce.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private IProductRepository productRepository;
    private IProductImageService productImageService;
    private ICategoryRepository categoryRepository;
    private IProductImageRepository productImageRepository;
    private IDiscountRepository discountRepository;
    ProductMapper productMapper;

    public ProductService(IProductRepository productRepository,
                          IProductImageService productImageService,
                          ICategoryRepository categoryRepository,
                          IProductImageRepository productImageRepository,
                          IDiscountRepository discountRepository){
        this.productRepository = productRepository;
        this.productImageService = productImageService;
        this.categoryRepository = categoryRepository;
        this.productImageService = productImageService;
        this.discountRepository = discountRepository;
        this.productMapper = new ProductMapper(categoryRepository, productImageRepository, discountRepository);
    }



    @Override
    public ProductDTO creatProduct(ProductDTO productDTO, MultipartFile[] images) {
        Products product = productMapper.convertDTOToProduct(productDTO, images);
        Products saveProduct = productRepository.save(product);
        List<ProductImages> productImages = product.getProductImages();
        ProductImageMapper productImageMapper = new ProductImageMapper();
        CloudinaryConfig clouddinary = new CloudinaryConfig();
        for(MultipartFile image : images) {
            try {
                Map<?, ?> results = clouddinary.configCloudinary().uploader()
                        .upload(image.getBytes(), ObjectUtils.emptyMap());
                ProductImageDTO saveImageDto = new ProductImageDTO();
                saveImageDto.setProduct(saveProduct.getProductId());
                saveImageDto.setImageUrl(results.get("secure_url").toString());
                productImageService.addProductImage(saveImageDto);
                productDTO.getProductImages().add(saveImageDto);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        saveProduct = productRepository.save(product);
        return productMapper.convertProductToDTO(saveProduct);
    }

    @Override
    public ProductDTO getProductId(String productId) {
        Products product = productRepository.findByProductIdAndActiveFlagIsTrueAndDeleteFlagIsFalse(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product is not exists with given id: " + productId));
        return productMapper.convertProductToDTO(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Products> products = productRepository.findAllByAndActiveFlagIsTrueAndDeleteFlagIsFalse();
        return products.stream()
                .map(productMapper::convertProductToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(String productId, ProductDTO updateProduct) {
        Products product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product is not exists with given id: " + productId));

        product.setProductName(updateProduct.getProductName());
        product.setDescription(updateProduct.getDescription());
        product.setPrice(updateProduct.getPrice());
        product.setSize(updateProduct.getSize());
        product.setBrand(updateProduct.getBrand());
        product.setAmount(updateProduct.getAmount());
        product.setCategory(productMapper.convertCategoryFromDTO(updateProduct.getCategory()));
//        product.setProductImages(ProductMapper.convertProductImageFromDTO(updateProduct.getProductImages()));
        product.setDiscounts(productMapper.converDiscountFromDTO(updateProduct.getDiscounts()));

        Products updateProductObj = productRepository.save(product);
        return productMapper.convertProductToDTO(updateProductObj);
    }

    @Override
    public void deleteProduct(String productId) {
        Products product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product is not exists with given id: " + productId));
        product.setActiveFlag(false);
        product.setDeleteFlag(true);
        productRepository.save(product);
    }
}
