package com.huangbusiness.service.impl;

import com.huangbusiness.common.dto.UserEntryDto;
import com.huangbusiness.security.jwt.JwtUtils;
import com.huangbusiness.security.user.MyUserDetails;
import com.huangbusiness.service.UserService;
import com.huangbusiness.common.vo.UserEntryVo;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
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
    public UserEntryVo login(@Valid UserEntryDto userEntryDto, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userEntryDto.getEmail(), userEntryDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();

        String token = jwtUtils.createJwt(userDetails);
        String refreshToken = jwtUtils.createRefresh(userDetails);

        ResponseCookie cookie = ResponseCookie.from("cookstore-jwt-refresh", refreshToken)
                .httpOnly(true)
                .path("/admin/refresh")
//                .sameSite("None")
//                .secure(true)
                .maxAge(24 * 60 * 60)
                .build();
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return UserEntryVo.builder().token(token).build();
    }

    @Override
    public UserEntryVo refresh(String refreshToken) {
        String newToken = jwtUtils.refresh(refreshToken);
        return UserEntryVo.builder().token(newToken).build();
    }
}