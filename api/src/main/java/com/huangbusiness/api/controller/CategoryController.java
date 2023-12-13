package com.huangbusiness.api.controller;

import com.huangbusiness.common.result.Result;
import com.huangbusiness.common.vo.CategoryVo;
import com.huangbusiness.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public Result<List<CategoryVo>> getCategories() {
        List<CategoryVo> vo = categoryService.getCategories();
        return Result.success(vo);
    }

    @GetMapping("/{id}")
    public Result<CategoryVo> getCategory(@PathVariable("id") int id) {
        CategoryVo vo = categoryService.getCategory(id);
        return Result.success(vo);
    }

    @GetMapping("/{id}/products")
    public Result<CategoryVo> getCategoryWithProducts(@PathVariable("id") int id){
        return Result.success(categoryService.getCategoryWithProducts(id));
    }
}
