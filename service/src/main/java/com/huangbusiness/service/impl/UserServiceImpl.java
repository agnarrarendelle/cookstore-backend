package com.huangbusiness.service.impl;

import com.huangbusiness.common.UserEntryDto;
import com.huangbusiness.security.jwt.JwtUtils;
import com.huangbusiness.security.user.MyUserDetails;
import com.huangbusiness.service.UserService;
import com.huangbusiness.common.vo.UserEntryVo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class UserServiceImpl implements UserService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    @Validated
    public UserEntryVo login(@Valid UserEntryDto userEntryDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userEntryDto.getEmail(), userEntryDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();

        String token = jwtUtils.createJwt(userDetails);
        return UserEntryVo.builder().token(token).build();
    }
}