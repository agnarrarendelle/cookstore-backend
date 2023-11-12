package com.huangbusiness.api.controller;

import com.huangbusiness.common.result.Result;
import com.huangbusiness.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_Admin')")
    public Result<Void> addCategory(@RequestBody String name){
        categoryService.addCategory(name);
        return Result.success();
    }
}
