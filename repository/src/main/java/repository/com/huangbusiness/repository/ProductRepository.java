package repository.com.huangbusiness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import repository.com.huangbusiness.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
