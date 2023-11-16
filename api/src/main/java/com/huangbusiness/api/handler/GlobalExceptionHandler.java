package com.huangbusiness.api.handler;

import com.huangbusiness.common.exception.CategoryNotFoundException;
import com.huangbusiness.common.exception.ProductNotExistException;
import com.huangbusiness.common.result.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity<?> exception(ProductNotExistException exception) {
        return Result.error(exception.getMessage(), ProductNotExistException.httpStatus);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<?> exception(CategoryNotFoundException exception) {
        return Result.error(exception.getMessage(), CategoryNotFoundException.httpStatus);
    }
}
