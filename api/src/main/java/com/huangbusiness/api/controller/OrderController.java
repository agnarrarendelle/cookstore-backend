package com.huangbusiness.api.controller;

import com.huangbusiness.common.dto.OrderDto;
import com.huangbusiness.common.dto.OrderItemDto;
import com.huangbusiness.common.result.Result;
import com.huangbusiness.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public Result<Void> createOrder(@RequestBody OrderDto orderDto){
        orderService.createOrder(orderDto);
        return Result.success();
    }
}