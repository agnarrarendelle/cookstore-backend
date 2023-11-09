package repository.com.huangbusiness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import repository.com.huangbusiness.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
