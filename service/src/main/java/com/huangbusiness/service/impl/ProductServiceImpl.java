package com.huangbusiness.service.impl;

import com.huangbusiness.common.dto.ProductDto;
import com.huangbusiness.common.exception.ProductNotExistException;
import com.huangbusiness.repository.entity.Category;
import com.huangbusiness.repository.entity.Product;
import com.huangbusiness.repository.repositories.ProductRepository;
import com.huangbusiness.service.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void addProduct(@Valid ProductDto productDto) {
        Product product = Product
                .builder()
                .name(productDto.getName())
                .status(Product.ProductStatus.Unavailable)
                .soldNumber(0)
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .discount(productDto.getDiscount())
                .category(entityManager.getReference(Category.class, productDto.getCategoryId()))
                .build();

        productRepository.save(product);
    }

    @Override
    @Transactional
    @Validated({Default.class, ProductDto.UpdateProductAction.class})
    public void updateProduct(@Valid ProductDto productDto) {
        Product product = productRepository.findById(productDto.getProductId()).orElseThrow(() -> new ProductNotExistException(productDto.getName()));
        product.setName(product.getName());
        product.setStatus(productDto.getIsAvailable() ? Product.ProductStatus.Available : Product.ProductStatus.Unavailable);
        product.setDescription(product.getDescription());
        product.setPrice(productDto.getPrice());
        product.setDiscount(productDto.getDiscount());
        product.setCategory(entityManager.getReference(Category.class, productDto.getCategoryId()));

        productRepository.save(product);
    }
}
