package com.huangbusiness.common.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductImageVo {
    private String uploadUrl;
    private String imageId;
    private String imageUrl;
    private String imageName;
}
