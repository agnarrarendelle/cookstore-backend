package com.huangbusiness.service.impl;

import com.huangbusiness.common.dto.CategoryDto;
import com.huangbusiness.common.vo.CategoryVo;
import com.huangbusiness.repository.mapper.CategoryMapper;
import com.huangbusiness.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.huangbusiness.repository.entity.Category;
import com.huangbusiness.repository.repositories.CategoryRepository;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    @Transactional
    public void addCategory(@Valid CategoryDto categoryDto) {
        Category newCategory = Category.builder().name(categoryDto.getName()).build();
        categoryRepository.save(newCategory);
    }

    @Override
    public List<CategoryVo> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toVo(categories);
    }
}
