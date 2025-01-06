package com.gaboot.gabshop.api.gateway.product;

import com.gaboot.gabshop.api.gateway.common.dto.PaginationDto;
import com.gaboot.gabshop.api.gateway.common.dto.ResponseDto;
import com.gaboot.gabshop.api.gateway.product.dto.CreateProductDto;
import com.gaboot.gabshop.api.gateway.product.dto.ProductDto;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/products")
@Validated
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
    public ResponseEntity<ResponseDto<ProductDto>> paginate(@ModelAttribute PaginationDto paging) {
        return ResponseEntity.ok(productService.paginate(paging));
    }

    @PostMapping()
    public ResponseEntity<ResponseDto<ProductDto>> create(
            @Valid @RequestPart("product") CreateProductDto productDto,
            @RequestPart("file") MultipartFile file
    ) {
        if (file.isEmpty()) {
            try {
                throw new BadRequestException("Please select a file to upload.");
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        }

        return ResponseEntity.ok(productService.create(productDto, file));
    }
}
