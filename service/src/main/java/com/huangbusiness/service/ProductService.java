package com.huangbusiness.service;

import com.huangbusiness.common.dto.ProductDto;
import jakarta.validation.Valid;

public interface ProductService {
    void addProduct(@Valid ProductDto productDto);

    void updateProduct(@Valid ProductDto productDto);
}