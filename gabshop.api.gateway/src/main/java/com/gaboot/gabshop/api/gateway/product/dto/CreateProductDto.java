package com.gaboot.gabshop.api.gateway.product.dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@ToString
@Accessors(chain = true)
public class CreateProductDto {
    private String name;
    private String sku;
    private String unit;
    private Double price;
    private Double stock;
    private Boolean isActive;
}
