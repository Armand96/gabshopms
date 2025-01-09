package com.gaboot.gabshop.api.gateway.product;

import com.gaboot.gabshop.api.gateway.common.dto.PaginationDto;
import com.gaboot.gabshop.api.gateway.common.dto.ResponseDto;
import com.gaboot.gabshop.api.gateway.product.dto.CreateProductDto;
import com.gaboot.gabshop.api.gateway.product.dto.FilterProductDto;
import com.gaboot.gabshop.api.gateway.product.dto.ProductDto;
import com.gaboot.gabshop.api.gateway.product.dto.UpdateProductDto;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<ResponseDto<ProductDto>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<ProductDto>> findOne(@PathVariable long id) {
        return ResponseEntity.ok(productService.findOne(id));
    }

    @GetMapping("/paging")
    public ResponseEntity<ResponseDto<ProductDto>> paginate(@ModelAttribute FilterProductDto filter) {
        return ResponseEntity.ok(productService.paginate(filter));
    }

    @PostMapping()
    public ResponseEntity<ResponseDto<ProductDto>> create(
            @RequestPart("product") CreateProductDto productDto,
            @RequestPart("file") MultipartFile file
    ) {
        return ResponseEntity.ok(productService.create(productDto, file));
    }

    @PutMapping()
    public ResponseEntity<ResponseDto<ProductDto>> update(
            @RequestPart("product") UpdateProductDto productDto,
            @RequestPart("file") MultipartFile file
    ) {
        return ResponseEntity.ok(productService.update(productDto, file));
    }
}
