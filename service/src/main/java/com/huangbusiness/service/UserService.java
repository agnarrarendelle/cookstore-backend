package com.huangbusiness.service;

import com.huangbusiness.common.dto.UserEntryDto;
import com.huangbusiness.common.vo.UserEntryVo;
import jakarta.validation.Valid;

public interface UserService {
    public UserEntryVo login(@Valid UserEntryDto userEntryDto);
}