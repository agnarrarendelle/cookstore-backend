package com.huangbusiness.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.huangbusiness.common.exception.JwtAuthException;
import com.huangbusiness.security.user.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import jakarta.servlet.http.Cookie;

import javax.naming.AuthenticationException;

@Component
public class JwtUtils {
    @Value("${jwt.secret_key}")
    String key;

    @Value("${jwt.expire_day}")
    int expireDay;

    @Value("${jwt.expire_hour}")
    int expireHour;

    @Autowired
    UserDetailsService userDetailsService;

    public String createJwt(MyUserDetails userDetails) {
        Date expire = expireHour(expireHour);
        return createJwt(userDetails, expire);
    }

    public String createRefresh(MyUserDetails userDetails) {
        Date expire = expireDay(expireDay);
        return createJwt(userDetails, expire);
    }

    private String createJwt(MyUserDetails userDetails, Date expire) {
        Algorithm algorithm = Algorithm.HMAC256(key);
        return JWT.create()
                .withJWTId(UUID.randomUUID().toString())
                .withClaim("id", userDetails.getUserId())
                .withClaim("email", userDetails.getEmail())
                .withExpiresAt(expire)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    private Date expireHour(int expireHour) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, expireHour);
        return calendar.getTime();
    }

    private Date expireDay(int expireDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, expireDay * 24);
        return calendar.getTime();
    }

    public DecodedJWT resolve(String headerToken) {
        String token = convertToken(headerToken);
        if (token == null)
            return null;

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(key)).build();

        try {
            DecodedJWT jwt = jwtVerifier.verify(token);
            Date expiredAt = jwt.getExpiresAt();
            return new Date().after(expiredAt) ? null : jwt;
        } catch (JWTVerificationException e) {
            throw new JwtAuthException();
        }
    }

    private static String convertToken(String headerToken) {
        if (headerToken == null || !headerToken.startsWith("Bearer "))
            return null;
        return headerToken.substring(7);
    }

    public UserDetails toUser(DecodedJWT jwt) {
        Map<String, Claim> claims = jwt.getClaims();
        String email = claims.get("email").asString();
        return userDetailsService.loadUserByUsername(email);
    }

    public String refresh(String refreshToken) {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(key)).build();
            DecodedJWT decodedRefreshToken = jwtVerifier.verify(refreshToken);
            UserDetails userDetails = toUser(decodedRefreshToken);
            return createJwt((MyUserDetails) userDetails);
        } catch (JWTVerificationException e) {
            throw new JwtAuthException();
        }
    }
}
