package com.sekoding.example.demo.security.jwt;

import com.sekoding.example.demo.model.entity.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${java.app.jwtSecret}")
    private String jwtSecret;

    @Value("${java.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(User user) {
        return Jwts
            .builder()
            .setSubject(user.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }

}
