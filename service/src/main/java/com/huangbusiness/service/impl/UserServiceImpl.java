package com.huangbusiness.service.impl;

import com.huangbusiness.security.jwt.JwtUtils;
import com.huangbusiness.security.user.MyUserDetails;
import com.huangbusiness.service.UserService;
import com.huangbusiness.common.vo.UserEntryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public UserEntryVo login(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();

        String token = jwtUtils.createJwt(userDetails);
        return UserEntryVo.builder().token(token).build();
    }
}