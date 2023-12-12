package com.huangbusiness.common.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ProductDto {

    public static interface UpdateProductAction {
    }

    @NotNull(groups = UpdateProductAction.class)
    private Integer productId;

    @NotBlank
    @Size(min = 1, max = 128)
    private String name;

    @Size(min = 0, max = 512)
    private String description;

    @NotNull
    @Digits(integer = 4, fraction = 2)
    @DecimalMin("0.00")
    private BigDecimal price;

    @Digits(integer = 1, fraction = 2)
    @DecimalMax("1")
    @DecimalMin("0")
    private BigDecimal discount;

    @NotNull
    @Min(1)
    private Integer categoryId;

    @NotNull(groups = UpdateProductAction.class)
    private Boolean isAvailable;

    private UUID productImageId;
}
