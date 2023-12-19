package com.huangbusiness.service.event.listener;

import com.huangbusiness.service.OrderService;
import com.huangbusiness.service.event.OrderSubmitEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderSubmitEventListener {

    @Autowired
    OrderService orderService;
    @EventListener
    public void handleOrderSubmitEventListener(OrderSubmitEvent event){
        System.out.println(event);
        orderService.notifyAdminOrderSubmit(event.getOrder());
    }
}
