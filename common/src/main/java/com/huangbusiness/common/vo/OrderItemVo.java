package com.huangbusiness.common.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OrderItemVo {
    private String productName;
    private Integer number;
    private BigDecimal totalAmount;
    private BigDecimal actualAmount;
    private BigDecimal discount;
}
