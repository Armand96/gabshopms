package com.gaboot.exception;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrpcGlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GrpcGlobalExceptionHandler.class);

    @GrpcExceptionHandler(GrpcResourceNotFoundException.class)
    public StatusRuntimeException handleNotFound(GrpcResourceNotFoundException e) {
        logger.warn("Resource Not Found: {}", e.getMessage());
        return Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException();
    }

    @GrpcExceptionHandler(IllegalArgumentException.class)
    public StatusRuntimeException handleBadRequest(IllegalArgumentException e) {
        logger.warn("Invalid Argument: {}", e.getMessage());
        return Status.INVALID_ARGUMENT.withDescription(e.getMessage()).asRuntimeException();
    }

    @GrpcExceptionHandler(Exception.class)
    public StatusRuntimeException handleGenericException(Exception e) {
        logger.error("Internal Server Error: {}", e.getMessage(), e);
        return Status.INTERNAL.withDescription("An unexpected error occurred.").asRuntimeException();
    }
}
