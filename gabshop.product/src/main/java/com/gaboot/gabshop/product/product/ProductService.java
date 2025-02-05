package com.gaboot.gabshop.product.product;

// import org.springframework.grpc.server.service.GrpcService;

// import com.gaboot.gabshop.grpc.general.Pagination;
import com.gaboot.exception.GrpcResourceNotFoundException;
import com.gaboot.gabshop.grpc.product.*;
// import com.gaboot.gabshop.grpc.product.ProductsServiceGrpc;
import com.gaboot.gabshop.grpc.product.ProductsServiceGrpc.ProductsServiceImplBase;
// import com.gaboot.gabshop.product.services.ImageService;
// import com.gaboot.gabshop.product.services.ImageService;
import com.gaboot.services.files.FileServiceFactory;
import com.gaboot.services.images.ImageService;
// import com.gaboot.services.files.MinIoFileService;
import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
// import jakarta.annotation.PostConstruct;
import jakarta.annotation.PostConstruct;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.io.IOException;
import java.util.List;

@GrpcService
public class ProductService extends ProductsServiceImplBase {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductMapper prodMap;

    @Value("${bucket.url}")
    private String bucketUrl;

    @Value("${bucket.key}")
    private String bucketKey;

    @Value("${bucket.user}")
    private String bucketUser;

    @Value("${bucket.name}")
    private String bucketName;

    @Value("${bucket.type}")
    private String bucketType;

    @Autowired
    private ProductSpec productSpec;

    private ImageService imgSvc; // Let Spring inject ImageService

    @PostConstruct
    private void init() {
        this.imgSvc = new ImageService(
                new FileServiceFactory(bucketUrl, bucketUser, bucketKey, bucketName)
                        .getFileService(bucketType)
        );
    }

    @Autowired
    public ProductService(ProductRepo productRepo, ProductMapper prodMap, ProductSpec productSpec) {
        this.productRepo = productRepo;
        this.prodMap = prodMap;
        this.productSpec = productSpec;
    }

    @Override
    public void findAll(Empty request, StreamObserver<Products> responseObserver) {
        try {
            final List<Product> products = productRepo.findAll().stream().map(prodMap::toGRPC).toList();
            Products prods = Products.newBuilder().addAllProducts(products).build();
            responseObserver.onNext(prods);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription("Unexpected error").asRuntimeException());
        }
    }

    @Override
    public void findOne(Int64Value request, StreamObserver<Product> responseObserver) {
        try {
            final Product data = prodMap.toGRPC(productRepo.findById((long) request.getValue()).orElseThrow(() -> new GrpcResourceNotFoundException("Product not found")));
            responseObserver.onNext(data);
            responseObserver.onCompleted();
        } catch (GrpcResourceNotFoundException e) {
            responseObserver.onError(Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription("Unexpected error").asRuntimeException());
        }
    }

    @Override
    public void findPaginate(FilterProduct request, StreamObserver<PagingProduct> responseObserver) {

        try {
            final int page = request.getPaging().getPage()-1;
            final int pageSize = request.getPaging().getDataPerPage();

            final String name = request.getName();
            final String sku = request.getSku();

            Specification<ProductEntity> spec = productSpec.filterByNameAndSku(name, sku);
            Page<ProductEntity> productPages = productRepo.findAll(spec, PageRequest.of(page, pageSize));
            List<Product> grpcProducts = productPages.getContent().stream().map(prodMap::toGRPC).toList();
            PagingProduct paging = PagingProduct.newBuilder().addAllProducts(grpcProducts).setLastPage(productPages.getTotalPages()).build();

            responseObserver.onNext(paging);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription("Unexpected error").asRuntimeException());
        }
    }

    @Override
    public void create(CreateProduct request, StreamObserver<Product> responseObserver) {
        try {
            String defaultImagePath = "";
            String defaultImageThumbPath = "";
            if(!request.getImageContent().isEmpty()) {
                defaultImagePath = imgSvc.saveImage(request.getImageContent(), request.getFileName(), "products/img/");
                defaultImageThumbPath = imgSvc.uploadImageThumb(request.getImageContent(), request.getFileName(), "products/img/thumb/");
            }

            final ProductEntity productEntity = prodMap.toEntity(request);
            productEntity.setDefaultImage(defaultImagePath);
            productEntity.setDefaultImageThumb(defaultImageThumbPath);
            productRepo.save(productEntity);

            Product product = prodMap.toGRPC(productEntity);
            responseObserver.onNext(product);
            responseObserver.onCompleted();
        } catch (IOException e) {
            responseObserver.onError(Status.INTERNAL.withDescription("Unexpected error").asRuntimeException());
        }
    }

    @Override
    public void update(UpdateProduct request, StreamObserver<Product> responseObserver) {
        try {
            String defaultImagePath = "";
            String defaultImageThumbPath = "";
            if(!request.getProduct().getImageContent().isEmpty()) {
                defaultImagePath = imgSvc.saveImage(request.getProduct().getImageContent(), request.getProduct().getFileName(), "products/img/");
                defaultImageThumbPath = imgSvc.uploadImageThumb(request.getProduct().getImageContent(), request.getProduct().getFileName(), "products/img/thumb/");
            }

            ProductEntity productEntity = productRepo.findById(request.getId()).orElseThrow(() -> new RuntimeException("Product not found"));
            productEntity = prodMap.toEntity(request.getProduct());
            productEntity.setId(request.getId());
            if(!defaultImagePath.isEmpty() || !defaultImageThumbPath.isEmpty()) {
                productEntity.setDefaultImage(defaultImagePath);
                productEntity.setDefaultImageThumb(defaultImageThumbPath);
            }
            productRepo.save(productEntity);

            Product product = prodMap.toGRPC(productEntity);
            responseObserver.onNext(product);
            responseObserver.onCompleted();
        } catch (IOException e) {
            responseObserver.onError(Status.INTERNAL.withDescription("Unexpected error").asRuntimeException());
        }
    }
}
