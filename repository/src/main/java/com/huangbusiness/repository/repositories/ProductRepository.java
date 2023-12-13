package com.huangbusiness.repository.repositories;

import com.huangbusiness.repository.entity.Category;
import com.huangbusiness.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByCategory(Category category);
}
