package com.gaboot.gabshop.api.gateway.product;

import com.gaboot.gabshop.api.gateway.product.dto.CreateProductDto;
import com.gaboot.gabshop.api.gateway.product.dto.ProductDto;
import com.gaboot.gabshop.grpc.product.CreateProduct;
import com.gaboot.gabshop.grpc.product.Product;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Component
public class ProductMapperRest {
    public ProductDto toDto(Product prod){
        ProductDto data = new ProductDto();
        return data
                .setId(prod.getId())
                .setName(prod.getName()) // Provide default empty string
                .setSku(prod.getSku())
                .setUnit(prod.getUnit())
                .setPrice(prod.getPrice()) // Default price to 0.0
                .setStock(prod.getStock()) // Default stock to 0
                .setDefaultImage(prod.getDefaultImage())
                .setDefaultImageThumb(prod.getDefaultImageThumb())
                .setCreatedAt(new Date(prod.getCreatedAt())) // Default to epoch 0
                .setUpdatedAt(new Date(prod.getUpdatedAt())) // Default to epoch 0
                .setIsActive(prod.getIsActive());
    }

    public Product toGRPC(ProductDto dto) {
        return Product.newBuilder()
                .setId(dto.getId())
                .setName(dto.getName() != null ? dto.getName() : "") // Provide default empty string
                .setSku(dto.getSku() != null ? dto.getSku() : "")
                .setUnit(dto.getUnit() != null ? dto.getUnit() : "")
                .setPrice(dto.getPrice() != null ? dto.getPrice() : 0.0) // Default price to 0.0
                .setStock(dto.getStock() != null ? dto.getStock() : 0) // Default stock to 0
                .setDefaultImage(dto.getDefaultImage() != null ? dto.getDefaultImage() : "")
                .setDefaultImageThumb(dto.getDefaultImageThumb() != null ? dto.getDefaultImageThumb() : "")
                .setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt().getTime() : 0L) // Default to epoch 0
                .setUpdatedAt(dto.getUpdatedAt() != null ? dto.getUpdatedAt().getTime() : 0L) // Default to epoch 0
                .setIsActive(dto.getIsActive() != null ? dto.getIsActive() : false) // Default to false
                .build();
    }

    public CreateProduct toCreateProductGRPC(CreateProductDto createProduct, MultipartFile file) {
        try {
            return CreateProduct.newBuilder()
                    .setImageContent(ByteString.copyFrom(file.getBytes()))
                    .setName(createProduct.getName())
                    .setSku(createProduct.getSku())
                    .setPrice(createProduct.getPrice())
                    .setUnit(createProduct.getUnit())
                    .setStock(createProduct.getStock())
                    .setIsActive(createProduct.getIsActive())
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
