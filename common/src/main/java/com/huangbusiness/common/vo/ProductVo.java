package com.huangbusiness.common.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductVo {
    private Integer id;
    private String name;
    private String status;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private BigDecimal discount;
}
