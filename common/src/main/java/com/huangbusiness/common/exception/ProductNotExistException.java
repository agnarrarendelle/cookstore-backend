package com.huangbusiness.common.exception;

import org.springframework.http.HttpStatus;

public class ProductNotExistException extends RuntimeException {

    public static HttpStatus httpStatus = HttpStatus.BAD_GATEWAY;

    public ProductNotExistException(String productName) {
        super(String.format("Product %s does not exist", productName));
    }
}
