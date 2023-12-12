package com.huangbusiness.api.controller.admin;

import com.huangbusiness.common.dto.ProductImageDto;
import com.huangbusiness.common.result.Result;
import com.huangbusiness.common.vo.ProductImageVo;
import com.huangbusiness.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController("adminProductImageController")
@RequestMapping("/admin/product-images")
public class ProductImageController {

    @Autowired
    ProductImageService productImageService;

    @GetMapping("/upload-url")
    public Result<ProductImageVo> getUploadUrl(){
        ProductImageVo url = productImageService.getUploadUrl();
        return Result.success(url);
    }

    @PostMapping()
    public Result<Void> saveImageDetails(@RequestBody ProductImageDto productImageDto){
         productImageService.saveImageDetails(productImageDto);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<ProductImageVo> getUploadUrl(@PathVariable("id")UUID id){
        ProductImageVo vo = productImageService.getImage(id);
        return Result.success(vo);
    }
}