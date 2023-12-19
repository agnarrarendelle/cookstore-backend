package com.huangbusiness.service.event;

import com.huangbusiness.common.vo.OrderVo;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderSubmitEvent extends ApplicationEvent {
    private final OrderVo order;

    public OrderSubmitEvent(Object source, OrderVo order) {
        super(source);
        this.order = order;
    }
}
