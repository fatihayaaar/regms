package com.fayardev.regms.profileservice.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class JwtUtils {

    private JwtUtils() {}

    public static String getUserUUID() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return jwt.getClaim("uuid");
    }

    public static String getBearerToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof JwtAuthenticationToken) {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            return jwt.getTokenValue();
        } else {
            return null;
        }
    }
}