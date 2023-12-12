package com.huangbusiness.api.controller.admin;

import com.huangbusiness.common.dto.UserEntryDto;
import com.huangbusiness.common.result.Result;
import com.huangbusiness.service.UserService;
import com.huangbusiness.common.vo.UserEntryVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;
    @PostMapping("/login")
    public Result<UserEntryVo> login(@RequestBody UserEntryDto userInfo,  HttpServletResponse response){
        UserEntryVo vo = userService.login(userInfo, response);
        return Result.success(vo);
    }

    @PostMapping("/refresh")
    public Result<UserEntryVo> login(@CookieValue("cookstore-jwt-refresh") String refreshToken){
        UserEntryVo vo = userService.refresh(refreshToken);
        return Result.success(vo);
    }

    @PostMapping("/validate")
    public Result<Void> validate(){
        return Result.success();
    }
}
