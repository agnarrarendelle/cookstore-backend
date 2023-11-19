package com.huangbusiness.api.controller;

import com.huangbusiness.common.dto.UserEntryDto;
import com.huangbusiness.common.result.Result;
import com.huangbusiness.service.UserService;
import com.huangbusiness.common.vo.UserEntryVo;
import jakarta.servlet.http.HttpServletResponse;
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
    public Result<UserEntryVo> login(@RequestBody UserEntryDto userInfo,  HttpServletResponse response){
        UserEntryVo vo = userService.login(userInfo, response);
        return Result.success(vo);
    }
}
