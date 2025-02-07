package com.gaboot.gabshop.api.gateway.product;

import com.gaboot.gabshop.api.gateway.common.dto.ResponseDto;
import com.gaboot.gabshop.api.gateway.product.dto.CreateProductDto;
import com.gaboot.gabshop.api.gateway.product.dto.FilterProductDto;
import com.gaboot.gabshop.api.gateway.product.dto.ProductDto;
import com.gaboot.gabshop.api.gateway.product.dto.UpdateProductDto;
import org.springframework.web.multipart.MultipartFile;

public interface ProductServiceInterfaceRest {
    public ResponseDto<ProductDto> findAll();
    public ResponseDto<ProductDto> findOne(long id);
    public ResponseDto<ProductDto> paginate(FilterProductDto filter);
    public ResponseDto<ProductDto> create(CreateProductDto productDto, MultipartFile file);
    public ResponseDto<ProductDto> update(UpdateProductDto productDto, MultipartFile file);
}
