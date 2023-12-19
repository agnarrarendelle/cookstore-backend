package com.huangbusiness.service.impl;

import com.huangbusiness.common.dto.OrderDto;
import com.huangbusiness.common.dto.OrderItemDto;
import com.huangbusiness.common.exception.OrderSystemNotReadyException;
import com.huangbusiness.common.vo.OrderItemVo;
import com.huangbusiness.common.vo.OrderVo;
import com.huangbusiness.service.event.OrderSubmitEvent;
import com.huangbusiness.common.exception.OrderNotExistException;
import com.huangbusiness.common.exception.OrderStatusInvalidException;
import com.huangbusiness.common.exception.ProductNotExistException;
import com.huangbusiness.repository.entity.Order;
import com.huangbusiness.repository.entity.OrderItem;
import com.huangbusiness.repository.entity.Product;
import com.huangbusiness.repository.repositories.OrderItemRepository;
import com.huangbusiness.repository.repositories.OrderRepository;
import com.huangbusiness.repository.repositories.ProductRepository;
import com.huangbusiness.service.OrderService;
import com.huangbusiness.service.handler.MyWebSocketHandler;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderItemRepository orderItemRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private MyWebSocketHandler myWebSocketHandler;

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

    @Override
    @Transactional
    public void createOrder(OrderDto orderDto) {
        if(!myWebSocketHandler.isAdminConnected())
            throw new OrderSystemNotReadyException();

        Order newOrder = new Order();

        BigDecimal totalAmount = new BigDecimal(0);
        BigDecimal actualAmount = new BigDecimal(0);

        List<OrderItem> orderItems = new ArrayList<>();

        Map<Integer, Product> orderProduct = productRepository.findProductsByStatus(
                orderDto.getOrderItems().stream().map(OrderItemDto::getProductId).toList(),
                Product.ProductStatus.Available
        ).stream().collect(Collectors.toMap(Product::getId, item -> item));

        for (int i = 0; i < orderDto.getOrderItems().size(); i++) {
            OrderItemDto currOrderItemProduct = orderDto.getOrderItems().get(i);
            Product currProduct = productRepository.findById(currOrderItemProduct.getProductId()).orElseThrow(() -> new ProductNotExistException(currOrderItemProduct.getProductId().toString()));

            OrderItem orderItem = new OrderItem();
            orderItem.setNumber(currOrderItemProduct.getNumber());
            orderItem.setTotalAmount(currProduct.getPrice().multiply(BigDecimal.valueOf(currOrderItemProduct.getNumber())));
            orderItem.setActualAmount(orderItem.getTotalAmount().multiply(currProduct.getDiscount()));
            orderItem.setDiscount(currProduct.getDiscount());

            if (!orderProduct.containsKey(currOrderItemProduct.getProductId())) {
                throw new ProductNotExistException(currOrderItemProduct.getProductName());
            }
            orderItem.setProduct(orderProduct.get(currOrderItemProduct.getProductId()));
            orderItem.setOrder(newOrder);

            orderItems.add(orderItem);

            totalAmount = totalAmount.add(orderItem.getTotalAmount());
            actualAmount = actualAmount.add(orderItem.getActualAmount());
        }

        newOrder.setStatus(Order.OrderStatus.Unpaid);
        newOrder.setTotalAmount(totalAmount);
        newOrder.setActualAmount(actualAmount);
        newOrder.setRemark(orderDto.getRemark());
        newOrder.setCustomerName(orderDto.getCustomerName());
        newOrder.setTableNumber(orderDto.getTableNumber());

        orderRepository.save(newOrder);
        orderItemRepository.saveAll(orderItems);

        List<OrderItemVo> orderItemVos = orderItems
                .stream()
                .map(o -> OrderItemVo
                        .builder()
                        .productName(o.getProduct().getName())
                        .number(o.getNumber())
                        .totalAmount(o.getTotalAmount())
                        .actualAmount(o.getActualAmount())
                        .discount(o.getDiscount())
                        .build())
                .toList();

        eventPublisher.publishEvent(new OrderSubmitEvent(this,
                OrderVo.builder()
                        .id(newOrder.getId())
                        .status(newOrder.getStatus().toString())
                        .totalAmount(totalAmount)
                        .actualAmount(actualAmount)
                        .remark(newOrder.getRemark())
                        .customerName(newOrder.getCustomerName())
                        .tableNumber(newOrder.getTableNumber())
                        .orderItems(orderItemVos)
                        .build()));
    }

    @Override
    public void notifyAdminOrderSubmit(OrderVo order) {
        simpMessagingTemplate.convertAndSendToUser("matt", "/new_order", order);
    }
}
