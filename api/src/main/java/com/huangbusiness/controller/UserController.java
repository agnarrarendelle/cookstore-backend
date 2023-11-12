package com.huangbusiness.controller;

import com.huangbusiness.dto.UserEntryDto;
import com.huangbusiness.result.Result;
import com.huangbusiness.service.UserService;
import com.huangbusiness.vo.UserEntryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/login")
    public Result<UserEntryVo> login(@RequestBody UserEntryDto userInfo){
        UserEntryVo vo = userService.login(userInfo.getEmail(), userInfo.getPassword());
        return Result.success(vo);
    }
}
