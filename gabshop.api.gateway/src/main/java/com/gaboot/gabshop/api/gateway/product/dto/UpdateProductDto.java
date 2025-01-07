package com.gaboot.gabshop.api.gateway.product.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@ToString
public class UpdateProductDto extends CreateProductDto {
    private long id;
}
