package com.huangbusiness.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.huangbusiness.security.user.MyUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import jakarta.servlet.http.Cookie;

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

    public DecodedJWT resolve(HttpServletRequest request, HttpServletResponse response) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        Cookie refreshToken = WebUtils.getCookie(request, "cookstore-jwt-refresh");

        String token = convertToken(authorization);
        if (token == null && refreshToken == null)
            return null;

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(key)).build();

        try {
            DecodedJWT jwt = jwtVerifier.verify(token);
            Date expiredAt = jwt.getExpiresAt();
            return new Date().after(expiredAt) ? null : jwt;
        } catch (JWTVerificationException e) {
            if (refreshToken == null) return null;

            try {
                DecodedJWT decodedRefreshToken = jwtVerifier.verify(refreshToken.getValue());
                UserDetails userDetails = toUser(decodedRefreshToken);
                String newToken = createJwt((MyUserDetails) userDetails);
                response.setHeader("Authorization", String.format("Bearer %s", newToken));
                return decodedRefreshToken;
            } catch (JWTVerificationException ex) {
                return null;
            }
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
}
