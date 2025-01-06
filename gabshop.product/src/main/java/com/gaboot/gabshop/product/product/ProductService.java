package com.gaboot.gabshop.product.product;

// import org.springframework.grpc.server.service.GrpcService;

import com.gaboot.gabshop.grpc.general.Pagination;
import com.gaboot.gabshop.grpc.product.CreateProduct;
import com.gaboot.gabshop.grpc.product.PagingProduct;
import com.gaboot.gabshop.grpc.product.Product;
import com.gaboot.gabshop.grpc.product.Products;
// import com.gaboot.gabshop.grpc.product.ProductsServiceGrpc;
import com.gaboot.gabshop.grpc.product.ProductsServiceGrpc.ProductsServiceImplBase;
import com.gaboot.gabshop.product.services.ImageService;
import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.util.List;

@GrpcService
public class ProductService extends ProductsServiceImplBase {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductMapper prodMap;

    @Autowired
    private ImageService imgSvc;

    @Override
    public void findAll(Empty request, StreamObserver<Products> responseObserver) {
        final List<Product> products = productRepo.findAll().stream().map(prodMap::toGRPC).toList();
        Products prods = Products.newBuilder().addAllProducts(products).build();
        responseObserver.onNext(prods);
        responseObserver.onCompleted();
    }

    @Override
    public void findOne(Int64Value request, StreamObserver<Product> responseObserver) {
        final Product data = prodMap.toGRPC(productRepo.findById((long) request.getValue()).orElseThrow(() -> new RuntimeException("Product not found")));
        responseObserver.onNext(data);
        responseObserver.onCompleted();
    }

    @Override
    public void findPaginate(Pagination request, StreamObserver<PagingProduct> responseObserver) {
        final int page = request.getPage()-1;
        final int pageSize = request.getDataPerPage();
        Page<ProductEntity> productPages = productRepo.findAll(PageRequest.of(page, pageSize));
        List<Product> grpcProducts = productPages.getContent().stream().map(prodMap::toGRPC).toList();
        PagingProduct paging = PagingProduct.newBuilder().addAllProducts(grpcProducts).setLastPage(productPages.getTotalPages()).build();

        responseObserver.onNext(paging);
        responseObserver.onCompleted();
    }

    @Override
    public void create(CreateProduct request, StreamObserver<Product> responseObserver) {
        try {
            final String defaultImagePath = imgSvc.saveFile(request.getImageContent(), request.getFileName(), "storage/products/img/");
            final String defaultImageThumbPath = imgSvc.uploadImageThumb(request.getImageContent(), request.getFileName(), "storage/products/img/thumb/");

            final ProductEntity productEntity = prodMap.toEntity(request);
            productEntity.setDefaultImage(defaultImagePath);
            productEntity.setDefaultImageThumb(defaultImageThumbPath);
            productRepo.save(productEntity);

            Product product = prodMap.toGRPC(productEntity);
            responseObserver.onNext(product);
            responseObserver.onCompleted();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
