package com.huangbusiness.service.impl;

import com.huangbusiness.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.huangbusiness.entity.Category;
import com.huangbusiness.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    @Transactional
    public void addCategory(String name) {
        Category newCategory = Category.builder().name(name).build();
        categoryRepository.save(newCategory);
    }
}
