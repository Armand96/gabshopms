package com.gaboot.gabshop.api.gateway.product;

import com.gaboot.gabshop.api.gateway.common.dto.PaginationDto;
import com.gaboot.gabshop.api.gateway.common.dto.ResponseDto;
import com.gaboot.gabshop.api.gateway.product.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ResponseDto<ProductDto>> paginate(@ModelAttribute PaginationDto paging) {
        return ResponseEntity.ok(productService.paginate(paging));
    }
}
