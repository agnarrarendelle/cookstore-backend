package com.huangbusiness.common.exception;

import org.springframework.http.HttpStatus;

import javax.naming.AuthenticationException;

public class JwtAuthException extends RuntimeException {
    public static HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

    public JwtAuthException() {
        super("Failed to authenticate");
    }
}
