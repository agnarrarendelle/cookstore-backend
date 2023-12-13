package com.huangbusiness.service;

import com.huangbusiness.common.dto.CategoryDto;
import com.huangbusiness.common.vo.CategoryVo;
import jakarta.validation.Valid;

import java.util.List;

public interface CategoryService {
    public void addCategory(@Valid CategoryDto name);

    List<CategoryVo> getCategories();

    CategoryVo getCategory(int id);

    CategoryVo getCategoryWithProducts(int id);
}
