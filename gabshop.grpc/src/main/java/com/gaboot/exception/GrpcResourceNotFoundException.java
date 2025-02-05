package com.gaboot.exception;

public class GrpcResourceNotFoundException extends RuntimeException {
    public GrpcResourceNotFoundException(String message) {
        super(message);
    }
}