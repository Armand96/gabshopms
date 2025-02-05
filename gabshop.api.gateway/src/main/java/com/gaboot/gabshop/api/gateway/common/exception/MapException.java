package com.gaboot.gabshop.api.gateway.common.exception;

import io.grpc.StatusRuntimeException;

public class MapException {

    public RuntimeException mapGrpcException(StatusRuntimeException e) {
        return switch (e.getStatus().getCode()) {
            case NOT_FOUND -> new ResourceNotFoundException("Product not found");
            case INVALID_ARGUMENT -> new IllegalArgumentException("Invalid request");
            default -> new RuntimeException("Internal server error");
        };
    }
}
