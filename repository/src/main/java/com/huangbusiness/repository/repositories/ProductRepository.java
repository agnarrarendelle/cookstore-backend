package com.huangbusiness.repository.repositories;

import com.huangbusiness.repository.entity.Category;
import com.huangbusiness.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByCategory(Category category);

    @Query("SELECT p FROM Product p WHERE p.id IN :productIds AND p.status = :status")
    List<Product> findProductsByStatus(@Param("productIds") List<Integer> productIds, @Param("status") Product.ProductStatus status);
}
