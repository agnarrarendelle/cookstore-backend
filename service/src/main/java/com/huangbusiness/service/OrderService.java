package com.huangbusiness.service;

import com.huangbusiness.common.dto.OrderDto;
import com.huangbusiness.common.vo.OrderVo;

public interface OrderService {
    void updateStatus(int id, String status);

    void notifyAdminOrderSubmit(OrderVo order);

    void createOrder(OrderDto orderDto);
}
