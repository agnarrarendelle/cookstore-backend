package com.huangbusiness.common.exception;

import org.springframework.http.HttpStatus;

public class OrderNotExistException extends RuntimeException{
    public static HttpStatus httpStatus = HttpStatus.BAD_GATEWAY;

    public OrderNotExistException(int id) {
        super(String.format("Order with id %s does not exist", id));
    }
}
