package com.gaboot.gabshop.grpc.product;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class ProductsServiceGrpc {

  private ProductsServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "ProductsService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.gaboot.gabshop.grpc.product.Products> getFindAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FindAll",
      requestType = com.google.protobuf.Empty.class,
      responseType = com.gaboot.gabshop.grpc.product.Products.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.gaboot.gabshop.grpc.product.Products> getFindAllMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, com.gaboot.gabshop.grpc.product.Products> getFindAllMethod;
    if ((getFindAllMethod = ProductsServiceGrpc.getFindAllMethod) == null) {
      synchronized (ProductsServiceGrpc.class) {
        if ((getFindAllMethod = ProductsServiceGrpc.getFindAllMethod) == null) {
          ProductsServiceGrpc.getFindAllMethod = getFindAllMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, com.gaboot.gabshop.grpc.product.Products>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FindAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gaboot.gabshop.grpc.product.Products.getDefaultInstance()))
              .setSchemaDescriptor(new ProductsServiceMethodDescriptorSupplier("FindAll"))
              .build();
        }
      }
    }
    return getFindAllMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gaboot.gabshop.grpc.general.Pagination,
      com.gaboot.gabshop.grpc.product.Products> getFindPaginateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FindPaginate",
      requestType = com.gaboot.gabshop.grpc.general.Pagination.class,
      responseType = com.gaboot.gabshop.grpc.product.Products.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gaboot.gabshop.grpc.general.Pagination,
      com.gaboot.gabshop.grpc.product.Products> getFindPaginateMethod() {
    io.grpc.MethodDescriptor<com.gaboot.gabshop.grpc.general.Pagination, com.gaboot.gabshop.grpc.product.Products> getFindPaginateMethod;
    if ((getFindPaginateMethod = ProductsServiceGrpc.getFindPaginateMethod) == null) {
      synchronized (ProductsServiceGrpc.class) {
        if ((getFindPaginateMethod = ProductsServiceGrpc.getFindPaginateMethod) == null) {
          ProductsServiceGrpc.getFindPaginateMethod = getFindPaginateMethod =
              io.grpc.MethodDescriptor.<com.gaboot.gabshop.grpc.general.Pagination, com.gaboot.gabshop.grpc.product.Products>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FindPaginate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gaboot.gabshop.grpc.general.Pagination.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gaboot.gabshop.grpc.product.Products.getDefaultInstance()))
              .setSchemaDescriptor(new ProductsServiceMethodDescriptorSupplier("FindPaginate"))
              .build();
        }
      }
    }
    return getFindPaginateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Int64Value,
      com.gaboot.gabshop.grpc.product.Product> getFindOneMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FindOne",
      requestType = com.google.protobuf.Int64Value.class,
      responseType = com.gaboot.gabshop.grpc.product.Product.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Int64Value,
      com.gaboot.gabshop.grpc.product.Product> getFindOneMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Int64Value, com.gaboot.gabshop.grpc.product.Product> getFindOneMethod;
    if ((getFindOneMethod = ProductsServiceGrpc.getFindOneMethod) == null) {
      synchronized (ProductsServiceGrpc.class) {
        if ((getFindOneMethod = ProductsServiceGrpc.getFindOneMethod) == null) {
          ProductsServiceGrpc.getFindOneMethod = getFindOneMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Int64Value, com.gaboot.gabshop.grpc.product.Product>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FindOne"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Int64Value.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gaboot.gabshop.grpc.product.Product.getDefaultInstance()))
              .setSchemaDescriptor(new ProductsServiceMethodDescriptorSupplier("FindOne"))
              .build();
        }
      }
    }
    return getFindOneMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ProductsServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProductsServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProductsServiceStub>() {
        @java.lang.Override
        public ProductsServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProductsServiceStub(channel, callOptions);
        }
      };
    return ProductsServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ProductsServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProductsServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProductsServiceBlockingStub>() {
        @java.lang.Override
        public ProductsServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProductsServiceBlockingStub(channel, callOptions);
        }
      };
    return ProductsServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ProductsServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProductsServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProductsServiceFutureStub>() {
        @java.lang.Override
        public ProductsServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProductsServiceFutureStub(channel, callOptions);
        }
      };
    return ProductsServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void findAll(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.gaboot.gabshop.grpc.product.Products> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindAllMethod(), responseObserver);
    }

    /**
     */
    default void findPaginate(com.gaboot.gabshop.grpc.general.Pagination request,
        io.grpc.stub.StreamObserver<com.gaboot.gabshop.grpc.product.Products> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindPaginateMethod(), responseObserver);
    }

    /**
     */
    default void findOne(com.google.protobuf.Int64Value request,
        io.grpc.stub.StreamObserver<com.gaboot.gabshop.grpc.product.Product> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindOneMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ProductsService.
   */
  public static abstract class ProductsServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ProductsServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ProductsService.
   */
  public static final class ProductsServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ProductsServiceStub> {
    private ProductsServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProductsServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProductsServiceStub(channel, callOptions);
    }

    /**
     */
    public void findAll(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.gaboot.gabshop.grpc.product.Products> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFindAllMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void findPaginate(com.gaboot.gabshop.grpc.general.Pagination request,
        io.grpc.stub.StreamObserver<com.gaboot.gabshop.grpc.product.Products> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFindPaginateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void findOne(com.google.protobuf.Int64Value request,
        io.grpc.stub.StreamObserver<com.gaboot.gabshop.grpc.product.Product> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFindOneMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ProductsService.
   */
  public static final class ProductsServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ProductsServiceBlockingStub> {
    private ProductsServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProductsServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProductsServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.gaboot.gabshop.grpc.product.Products findAll(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFindAllMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gaboot.gabshop.grpc.product.Products findPaginate(com.gaboot.gabshop.grpc.general.Pagination request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFindPaginateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gaboot.gabshop.grpc.product.Product findOne(com.google.protobuf.Int64Value request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFindOneMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ProductsService.
   */
  public static final class ProductsServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ProductsServiceFutureStub> {
    private ProductsServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProductsServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProductsServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gaboot.gabshop.grpc.product.Products> findAll(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFindAllMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gaboot.gabshop.grpc.product.Products> findPaginate(
        com.gaboot.gabshop.grpc.general.Pagination request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFindPaginateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gaboot.gabshop.grpc.product.Product> findOne(
        com.google.protobuf.Int64Value request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFindOneMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_FIND_ALL = 0;
  private static final int METHODID_FIND_PAGINATE = 1;
  private static final int METHODID_FIND_ONE = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FIND_ALL:
          serviceImpl.findAll((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<com.gaboot.gabshop.grpc.product.Products>) responseObserver);
          break;
        case METHODID_FIND_PAGINATE:
          serviceImpl.findPaginate((com.gaboot.gabshop.grpc.general.Pagination) request,
              (io.grpc.stub.StreamObserver<com.gaboot.gabshop.grpc.product.Products>) responseObserver);
          break;
        case METHODID_FIND_ONE:
          serviceImpl.findOne((com.google.protobuf.Int64Value) request,
              (io.grpc.stub.StreamObserver<com.gaboot.gabshop.grpc.product.Product>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getFindAllMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              com.gaboot.gabshop.grpc.product.Products>(
                service, METHODID_FIND_ALL)))
        .addMethod(
          getFindPaginateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.gaboot.gabshop.grpc.general.Pagination,
              com.gaboot.gabshop.grpc.product.Products>(
                service, METHODID_FIND_PAGINATE)))
        .addMethod(
          getFindOneMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Int64Value,
              com.gaboot.gabshop.grpc.product.Product>(
                service, METHODID_FIND_ONE)))
        .build();
  }

  private static abstract class ProductsServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ProductsServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.gaboot.gabshop.grpc.product.ProductProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ProductsService");
    }
  }

  private static final class ProductsServiceFileDescriptorSupplier
      extends ProductsServiceBaseDescriptorSupplier {
    ProductsServiceFileDescriptorSupplier() {}
  }

  private static final class ProductsServiceMethodDescriptorSupplier
      extends ProductsServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ProductsServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ProductsServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ProductsServiceFileDescriptorSupplier())
              .addMethod(getFindAllMethod())
              .addMethod(getFindPaginateMethod())
              .addMethod(getFindOneMethod())
              .build();
        }
      }
    }
    return result;
  }
}
