package com.heng.microservice.exception;

public class ProductNotFoundException  extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
