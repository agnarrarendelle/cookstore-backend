package com.huangbusiness.api.controller;

import com.huangbusiness.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order-item")
public class OrderItemController {
    @Autowired
    OrderItemService orderItemService;


}
