package com.huangbusiness.service;

import com.huangbusiness.vo.UserEntryVo;

public interface UserService {
    public UserEntryVo login(String email, String password);
}