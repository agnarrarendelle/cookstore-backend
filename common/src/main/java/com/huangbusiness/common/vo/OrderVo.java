package com.huangbusiness.common.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Builder
public class OrderVo {
    private Integer id;
    private String status;
    private BigDecimal totalAmount;
    private BigDecimal actualAmount;
    private String remark;
    private String customerName;
    private Short tableNumber;
    private List<OrderItemVo> orderItems;
}
