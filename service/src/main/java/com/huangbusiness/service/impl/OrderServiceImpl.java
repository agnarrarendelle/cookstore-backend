package com.huangbusiness.service.impl;

import com.huangbusiness.common.exception.OrderNotExistException;
import com.huangbusiness.common.exception.OrderStatusInvalidException;
import com.huangbusiness.repository.entity.Order;
import com.huangbusiness.repository.repositories.OrderRepository;
import com.huangbusiness.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    @Transactional
    public void updateStatus(int id, String status) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotExistException(id));
        try {
            order.setStatus(Order.OrderStatus.valueOf(status));
        } catch (IllegalArgumentException ex) {
            throw new OrderStatusInvalidException(status);
        }
    }
}
