package com.huangbusiness.api.controller;

import com.huangbusiness.common.dto.OrderItemDto;
import com.huangbusiness.common.result.Result;
import com.huangbusiness.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order-item")
public class OrderItemController {
    @Autowired
    OrderItemService orderItemService;

    @PostMapping
    public Result<Void> createOrder(@RequestBody OrderItemDto orderItemDto){
        orderItemService.createOrder(orderItemDto);
        return Result.success();
    }
}
