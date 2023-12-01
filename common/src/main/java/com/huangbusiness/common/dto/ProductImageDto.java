package com.huangbusiness.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ProductImageDto {
    private UUID id;
    private String name;
    private Integer productId;
}
