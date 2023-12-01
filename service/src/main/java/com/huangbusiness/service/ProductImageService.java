package com.huangbusiness.service;

import com.huangbusiness.common.dto.ProductImageDto;
import com.huangbusiness.common.vo.ProductImageVo;

import java.util.UUID;

public interface ProductImageService {
    ProductImageVo getUploadUrl();

    void saveImageDetails(ProductImageDto productImageDto);

    ProductImageVo getImage(UUID id);
}