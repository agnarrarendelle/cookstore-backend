package com.huangbusiness.common.exception;

import org.springframework.http.HttpStatus;

public class OrderSystemNotReadyException extends RuntimeException{
    public static HttpStatus httpStatus = HttpStatus.BAD_GATEWAY;

    public OrderSystemNotReadyException() {
        super("Order System Is Not Ready Yet!");
    }
}
