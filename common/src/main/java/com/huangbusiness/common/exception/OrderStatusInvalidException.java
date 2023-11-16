package com.huangbusiness.common.exception;

import org.springframework.http.HttpStatus;

public class OrderStatusInvalidException extends RuntimeException {
    public static HttpStatus httpStatus = HttpStatus.BAD_GATEWAY;

    public OrderStatusInvalidException(String status) {
        super(String.format("Status %s is not valid", status));
    }
}
