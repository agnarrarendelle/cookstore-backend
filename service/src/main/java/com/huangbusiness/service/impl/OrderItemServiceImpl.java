package com.huangbusiness.service.impl;

import com.huangbusiness.common.dto.OrderItemDto;
import com.huangbusiness.common.exception.ProductNotExistException;
import com.huangbusiness.repository.entity.Order;
import com.huangbusiness.repository.entity.OrderItem;
import com.huangbusiness.repository.entity.Product;
import com.huangbusiness.repository.repositories.OrderItemRepository;
import com.huangbusiness.repository.repositories.OrderRepository;
import com.huangbusiness.repository.repositories.ProductRepository;
import com.huangbusiness.service.OrderItemService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void createOrder(OrderItemDto orderItemDto) {
        Order newOrder = new Order();

        BigDecimal totalAmount = new BigDecimal(0);
        BigDecimal actualAmount = new BigDecimal(0);

        List<OrderItem> orderItems = new ArrayList<>();

        List<Product> orderProduct = productRepository.findAllById(
                orderItemDto.getOrderItemProducts().stream().map(OrderItemDto.OrderItemProduct::getProductId).toList()
        );

        for (int i = 0; i < orderItemDto.getOrderItemProducts().size(); i++) {
            OrderItemDto.OrderItemProduct currOrderItemProduct = orderItemDto.getOrderItemProducts().get(i);
            Product currProduct = productRepository.findById(currOrderItemProduct.getProductId()).orElseThrow(() -> new ProductNotExistException(currOrderItemProduct.getProductId().toString()));

            OrderItem orderItem = new OrderItem();
            orderItem.setNumber(currOrderItemProduct.getNumber());
            orderItem.setTotalAmount(currProduct.getPrice().multiply(BigDecimal.valueOf(currOrderItemProduct.getNumber())));
            orderItem.setActualAmount(orderItem.getTotalAmount().multiply(currProduct.getDiscount()));
            orderItem.setDiscount(currProduct.getDiscount());
            orderItem.setRemark(currOrderItemProduct.getRemark());
            orderItem.setProduct(entityManager.getReference(Product.class, currOrderItemProduct.getProductId()));
            orderItem.setOrder(newOrder);

            orderItems.add(orderItem);

            totalAmount = totalAmount.add(orderItem.getTotalAmount());
            actualAmount = actualAmount.add(orderItem.getActualAmount());
        }

        newOrder.setStatus(Order.OrderStatus.Unpaid);
        newOrder.setTotalAmount(totalAmount);
        newOrder.setActualAmount(actualAmount);
        newOrder.setRemark(orderItemDto.getRemark());
        newOrder.setCustomerName(orderItemDto.getCustomerName());
        newOrder.setTableNumber(orderItemDto.getTableNumber());

        orderRepository.save(newOrder);
        orderItemRepository.saveAll(orderItems);
    }
}
