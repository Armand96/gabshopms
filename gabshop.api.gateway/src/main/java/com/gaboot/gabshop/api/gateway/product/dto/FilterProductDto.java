package com.gaboot.gabshop.api.gateway.product.dto;

import com.gaboot.gabshop.api.gateway.common.dto.PaginationDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@Accessors(chain = true)
public class FilterProductDto extends PaginationDto{
    private String name = "";
    private String sku = "";
}
