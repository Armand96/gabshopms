package com.gaboot.gabshop.api.gateway.product;

import com.gaboot.gabshop.api.gateway.common.dto.ResponseDto;
import com.gaboot.gabshop.api.gateway.common.services.MappingService;
import com.gaboot.gabshop.api.gateway.product.dto.ProductDto;
import com.gaboot.gabshop.grpc.product.Product;
import com.gaboot.gabshop.grpc.product.ProductsServiceGrpc.ProductsServiceBlockingStub;
import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.devh.boot.grpc.client.inject.GrpcClient;

import java.util.List;

@Service
public class ProductService {

    @GrpcClient("productService")
    private ProductsServiceBlockingStub productStub;

    @Autowired
    private ProductMapperRest mapper;

    @Autowired
    private MappingService<ProductDto> mapServ;

    public ResponseDto<ProductDto> findAll(){
        Empty request = Empty.newBuilder().build();
        final List<ProductDto> result = productStub.findAll(request).getProductsList().stream().map(mapper::toDto).toList();
        final ResponseDto<ProductDto> respDto = new ResponseDto<>();
        mapServ.mapResponseSuccess(respDto, result, "");
        return respDto;
    }

    public ResponseDto<ProductDto> findOne(long id) {
        Int64Value request = Int64Value.newBuilder().setValue(id).build();
        final ProductDto product = mapper.toDto(productStub.findOne(request));
        final ResponseDto<ProductDto> respDto = new ResponseDto<>();
        mapServ.mapResponseSuccess(respDto, product, "");
        return respDto;
    }
}
