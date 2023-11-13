package com.huangbusiness.common.exception;

import org.springframework.http.HttpStatus;

public class ProductNotExistException extends RuntimeException{
    private final String message;

    public static HttpStatus httpStatus = HttpStatus.BAD_GATEWAY;

    public ProductNotExistException(String productName){
        this.message = String.format("Product %s does not exist", productName);
    }
}
