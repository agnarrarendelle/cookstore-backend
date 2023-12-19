package com.huangbusiness.common.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class OrderItemDto {
    private Integer productId;
    private Integer number;
    private String productName;
}
