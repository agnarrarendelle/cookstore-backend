package repository.com.huangbusiness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import repository.com.huangbusiness.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
