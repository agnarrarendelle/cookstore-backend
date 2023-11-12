package com.huangbusiness.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CategoryDto {
    @NotBlank
    private String name;
}