package com.huangbusiness.common.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CategoryVo {
    private Integer id;
    private String name;
    private List<ProductVo> products;
}
