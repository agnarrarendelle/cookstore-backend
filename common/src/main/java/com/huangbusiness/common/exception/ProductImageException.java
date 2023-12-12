package com.huangbusiness.common.exception;

import org.springframework.http.HttpStatus;

public class ProductImageException extends RuntimeException {
    public static HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    public ProductImageException() {
        super("Failed to get product image info");
    }
}
