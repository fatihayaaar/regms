package com.fayardev.regms.followservice.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class JwtUtils {

    private JwtUtils() {}

    public static String getUserUUID() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return jwt.getClaim("uuid");
    }
}