package com.gaboot.gabshop.api.gateway.product;

import com.gaboot.gabshop.api.gateway.common.dto.ResponseDto;
import com.gaboot.gabshop.api.gateway.common.services.MappingService;
import com.gaboot.gabshop.api.gateway.product.dto.CreateProductDto;
import com.gaboot.gabshop.api.gateway.product.dto.FilterProductDto;
import com.gaboot.gabshop.api.gateway.product.dto.ProductDto;
import com.gaboot.gabshop.api.gateway.product.dto.UpdateProductDto;
import com.gaboot.gabshop.grpc.general.Pagination;
import com.gaboot.gabshop.grpc.product.CreateProduct;
import com.gaboot.gabshop.grpc.product.FilterProduct;
import com.gaboot.gabshop.grpc.product.PagingProduct;
import com.gaboot.gabshop.grpc.product.ProductsServiceGrpc.ProductsServiceBlockingStub;
import com.gaboot.gabshop.grpc.product.UpdateProduct;
import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.multipart.MultipartFile;

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
        final Empty request = Empty.newBuilder().build();
        final List<ProductDto> result = productStub.findAll(request).getProductsList().stream().map(mapper::toDto).toList();
        final ResponseDto<ProductDto> respDto = new ResponseDto<>();
        mapServ.mapResponseSuccess(respDto, result, "");
        return respDto;
    }

    public ResponseDto<ProductDto> findOne(long id) {
        final Int64Value request = Int64Value.newBuilder().setValue(id).build();
        final ProductDto product = mapper.toDto(productStub.findOne(request));
        final ResponseDto<ProductDto> respDto = new ResponseDto<>();
        mapServ.mapResponseSuccess(respDto, product, "");
        return respDto;
    }

    public ResponseDto<ProductDto> paginate(FilterProductDto filter) {
        final FilterProduct req = FilterProduct.newBuilder()
                .setPaging(
                        Pagination.newBuilder()
                                .setDataPerPage(filter.getDataPerPage())
                                .setPage(filter.getPage()).build()
                ).setName(filter.getName()).setSku(filter.getSku()).build();
        final PagingProduct pageProd = productStub.findPaginate(req);
        final List<ProductDto> products = pageProd.getProductsList().stream().map(mapper::toDto).toList();
        final ResponseDto<ProductDto> respDto = new ResponseDto<>();
        mapServ.mapResponseSuccess(respDto, products, "", pageProd.getLastPage(), products.size());
        return respDto;
    }

    public ResponseDto<ProductDto> create(CreateProductDto productDto, MultipartFile file) {
        final CreateProduct req = mapper.toCreateProductGRPC(productDto, file);
        final ProductDto product = mapper.toDto(productStub.create(req));
        final ResponseDto<ProductDto> respDto = new ResponseDto<>();
        mapServ.mapResponseSuccess(respDto, product, "");
        return respDto;
    }

    public ResponseDto<ProductDto> update(UpdateProductDto productDto, MultipartFile file) {
        final UpdateProduct req = mapper.toUpdateProductGRPC(productDto, file);
        final ProductDto product = mapper.toDto(productStub.update(req));
        final ResponseDto<ProductDto> respDto = new ResponseDto<>();
        mapServ.mapResponseSuccess(respDto, product, "");
        return respDto;
    }
}
