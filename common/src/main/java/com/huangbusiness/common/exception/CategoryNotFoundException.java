package com.huangbusiness.common.exception;

import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends RuntimeException {

    public static HttpStatus httpStatus = HttpStatus.BAD_GATEWAY;

    public CategoryNotFoundException(int id) {
        super(String.format("Category with id %s does not exist", id));
    }
}
