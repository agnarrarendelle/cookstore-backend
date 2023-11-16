package com.huangbusiness.common.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class OrderItemDto {
    @Getter
    public static class OrderItemProduct {
        private Integer number;
        private Integer productId;
        private String remark;
    }

    private Short tableNumber;
    private String remark;
    private String customerName;
    private List<OrderItemProduct> orderItemProducts;
}
