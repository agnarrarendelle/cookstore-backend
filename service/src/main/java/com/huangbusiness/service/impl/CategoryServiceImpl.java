package com.huangbusiness.service.impl;

import com.huangbusiness.common.dto.CategoryDto;
import com.huangbusiness.common.exception.CategoryNotFoundException;
import com.huangbusiness.common.vo.CategoryVo;
import com.huangbusiness.common.vo.ProductImageVo;
import com.huangbusiness.common.vo.ProductVo;
import com.huangbusiness.repository.entity.Product;
import com.huangbusiness.repository.mapper.CategoryMapper;
import com.huangbusiness.repository.repositories.ProductRepository;
import com.huangbusiness.service.CategoryService;
import com.huangbusiness.service.ProductImageService;
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

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductImageService productImageService;

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

    @Override
    public CategoryVo getCategory(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        return categoryMapper.toVo(category);
    }

    @Override
    public CategoryVo getCategoryWithProducts(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        List<Product> products = productRepository.findAllByCategory(category);

        List<ProductVo> productVos = products.stream().map(p -> {
                    ProductImageVo productImageVo = productImageService.getImage(p.getProductImage().getId());
                    return ProductVo.builder()
                            .imageUrl(productImageVo.getImageUrl())
                            .name(p.getName())
                            .status(p.getStatus().toString())
                            .description(p.getDescription())
                            .price(p.getPrice())
                            .discount(p.getDiscount())
                            .build();
                }
        ).toList();

        return CategoryVo.builder()
                .id(id)
                .name(category.getName())
                .products(productVos)
                .build();
    }
}
