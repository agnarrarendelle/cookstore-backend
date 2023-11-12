package com.huangbusiness.api.controller;

import com.huangbusiness.common.result.Result;
import com.huangbusiness.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    public Result<Void> addCategory(@RequestBody String name){
        categoryService.addCategory(name);
        return Result.success();
    }
}
