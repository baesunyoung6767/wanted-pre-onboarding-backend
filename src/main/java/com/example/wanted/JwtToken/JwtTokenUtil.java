package com.example.wanted.JwtToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenUtil {
    private static long expiredTime = 1000 * 60 *60;

    public static String createToken(String userEmail, String key) {
        Claims claims = Jwts.claims();
        claims.put("userEmail", userEmail);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTime))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
}
