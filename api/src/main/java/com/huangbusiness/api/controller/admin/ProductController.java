package com.huangbusiness.api.controller.admin;

import com.huangbusiness.common.dto.ProductDto;
import com.huangbusiness.common.result.Result;
import com.huangbusiness.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("adminProductController")
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public Result<Void> addNewProduct(@RequestBody ProductDto productDto) {
        productService.addProduct(productDto);
        return Result.success();
    }

    @PutMapping("/{productId}")
    public Result<Void> updateProduct(@PathVariable("productId") int productId, @RequestBody ProductDto productDto) {
        productDto.setProductId(productId);
        productService.updateProduct(productDto);
        return Result.success();
    }
}