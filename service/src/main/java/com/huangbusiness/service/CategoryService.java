package com.huangbusiness.service;

import com.huangbusiness.common.dto.CategoryDto;
import jakarta.validation.Valid;

public interface CategoryService {
    public void addCategory(@Valid CategoryDto name);
}
