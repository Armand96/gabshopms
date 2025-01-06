package com.gaboot.gabshop.product.product;

import com.gaboot.gabshop.grpc.product.CreateProduct;
import com.gaboot.gabshop.grpc.product.Product;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ProductMapper {
    public Product toGRPC(ProductEntity ent) {
        return Product.newBuilder()
                .setId(ent.getId())
                .setName(ent.getName() != null ? ent.getName() : "") // Provide default empty string
                .setSku(ent.getSku() != null ? ent.getSku() : "")
                .setUnit(ent.getUnit() != null ? ent.getUnit() : "")
                .setPrice(ent.getPrice() != null ? ent.getPrice() : 0.0) // Default price to 0.0
                .setStock(ent.getStock() != null ? ent.getStock() : 0) // Default stock to 0
                .setDefaultImage(ent.getDefaultImage() != null ? ent.getDefaultImage() : "")
                .setDefaultImageThumb(ent.getDefaultImageThumb() != null ? ent.getDefaultImageThumb() : "")
                .setCreatedAt(ent.getCreatedAt() != null ? ent.getCreatedAt().getTime() : 0L) // Default to epoch 0
                .setUpdatedAt(ent.getUpdatedAt() != null ? ent.getUpdatedAt().getTime() : 0L) // Default to epoch 0
                .setIsActive(ent.getIsActive() != null ? ent.getIsActive() : false) // Default to false
                .build();
    }

    public ProductEntity toEntity(Product product) {
        product.getName();
        product.getSku();
        product.getUnit();
        product.getDefaultImage();
        product.getDefaultImageThumb();
        return new ProductEntity()
                .setId(product.getId())
                .setName(product.getName()) // Provide default empty string
                .setSku(product.getSku())
                .setUnit(product.getUnit())
                .setPrice(product.getPrice()) // Default price to 0.0
                .setStock(product.getStock()) // Default stock to 0
                .setDefaultImage(product.getDefaultImage())
                .setDefaultImageThumb(product.getDefaultImageThumb())
                .setCreatedAt(new Date(product.getCreatedAt())) // Default to epoch 0
                .setUpdatedAt(new Date(product.getUpdatedAt())) // Default to epoch 0
                .setIsActive(product.getIsActive());
    }

    public ProductEntity toEntity(CreateProduct product) {
        return new ProductEntity()
                .setName(product.getName()) // Provide default empty string
                .setSku(product.getSku())
                .setUnit(product.getUnit())
                .setPrice(product.getPrice()) // Default price to 0.0
                .setStock(product.getStock()) // Default stock to 0
                .setDefaultImage("")
                .setDefaultImageThumb("")
                .setCreatedAt(new Date()) // Default to epoch 0
                .setUpdatedAt(new Date()) // Default to epoch 0
                .setIsActive(product.getIsActive());
    }
}
