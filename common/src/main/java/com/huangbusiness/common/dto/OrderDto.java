package com.huangbusiness.common.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderDto {
    private String status;
    private Short tableNumber;
    private String remark;
    private String customerName;
    private List<OrderItemDto> orderItems;
}
