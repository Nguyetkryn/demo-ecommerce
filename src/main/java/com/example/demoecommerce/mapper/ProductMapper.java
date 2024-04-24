package com.example.demoecommerce.mapper;

import com.cloudinary.utils.ObjectUtils;
import com.example.demoecommerce.config.CloudinaryConfig;
import com.example.demoecommerce.dto.ProductDTO;
import com.example.demoecommerce.dto.ProductImageDTO;
import com.example.demoecommerce.entity.Categories;
import com.example.demoecommerce.entity.Discounts;
import com.example.demoecommerce.entity.ProductImages;
import com.example.demoecommerce.entity.Products;
import com.example.demoecommerce.exception.ResourceNotFoundException;
import com.example.demoecommerce.repository.ICategoryRepository;
import com.example.demoecommerce.repository.IDiscountRepository;
import com.example.demoecommerce.repository.IProductImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ProductMapper {

    private ICategoryRepository categoryRepository;
    private IProductImageRepository productImageRepository;
    private IDiscountRepository discountRepository;

    public ProductDTO convertProductToDTO(Products product){
        ProductDTO productDTO = new ProductDTO();

        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setSize(product.getSize());
        productDTO.setBrand(product.getBrand());
        productDTO.setAmount(product.getAmount());

        productDTO.setCategory(product.getCategory().getCategoryId());
        ProductImageMapper productImageMapper = new ProductImageMapper();
        List<ProductImageDTO> productImageDTOs = productImageMapper.toProductImageDTOList(product.getProductImages());
        productDTO.setProductImages(productImageDTOs);
//        productDTO.setDiscounts(product.getDiscounts().stream()
//                .map(Discounts::getDiscountId)
//                .collect(Collectors.toList()));

        return productDTO;
    }

    public Products convertDTOToProduct(ProductDTO productDTO, MultipartFile[] images){
        Products product = new Products();

        product.setProductId(productDTO.getProductId());
        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setSize(productDTO.getSize());
        product.setBrand(productDTO.getBrand());
        product.setAmount(productDTO.getAmount());

        product.setCategory(convertCategoryFromDTO(productDTO.getCategory()));
        ProductImageMapper productImageMapper = new ProductImageMapper();
        List<ProductImageDTO> savedProductImageDTO = new ArrayList<>();
        CloudinaryConfig clouddinary = new CloudinaryConfig();
        ProductImageDTO saveImageDto = new ProductImageDTO();
        for(MultipartFile image : images) {
            try{
                Map<?, ?> results = clouddinary.configCloudinary().uploader()
                        .upload(image.getBytes(), ObjectUtils.emptyMap());
                saveImageDto.setImageUrl(results.get("secure_url").toString());
                saveImageDto.setProduct(product.getProductId());
                savedProductImageDTO.add(saveImageDto);
            } catch (IOException exception){
                System.out.println(exception);
                return null;
            }
        }
        productDTO.setProductImages(savedProductImageDTO);
        List<ProductImages> productImages = productImageMapper.toProductImageList(productDTO.getProductImages());
        product.setProductImages(productImages);
        product.setDiscounts(null);

        return product;
    }

   public Categories convertCategoryFromDTO(String categoryId){
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + categoryId));
   }

    public List<ProductImages> convertProductImageFromDTO(List<String> productImageDTO){
        return productImageDTO.stream()
                .map(imageId -> productImageRepository.findById(imageId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Product Image not found: " + imageId)))
                .collect(Collectors.toList());
   }

    public List<Discounts> converDiscountFromDTO(List<String> discountDTO){
        return discountDTO.stream()
                .map(discountId -> discountRepository.findById(discountId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Discount not found: " + discountId)))
                .collect(Collectors.toList());
   }
}
