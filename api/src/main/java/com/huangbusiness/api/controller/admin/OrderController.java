package com.huangbusiness.api.controller.admin;

import com.huangbusiness.common.dto.OrderDto;
import com.huangbusiness.common.result.Result;
import com.huangbusiness.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("adminOrderController")
@RequestMapping("/admin/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PutMapping("/{id}/status")
    public Result<Void> updateStatus(@PathVariable("id") int id, @RequestBody OrderDto orderDto){
        orderService.updateStatus(id, orderDto.getStatus());
        return Result.success();
    }
}