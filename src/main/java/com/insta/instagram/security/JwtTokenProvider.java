package com.insta.instagram.security;

import com.insta.instagram.config.SecurityContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtTokenProvider {

    SecretKey key = Keys.hmacShaKeyFor(SecurityContext.JWT_KEY.getBytes());

    public String generateToken(Authentication auth){
        String jwt = Jwts.builder()
                .setIssuer("instagram")
                .setIssuedAt(new Date())
                .claim("username", auth.getName())
                .setExpiration(new Date(new Date().getTime() + 300000000))
                .signWith(key).compact();
        return jwt;
    }


    public JwtTokenClaims getClaimsFromToken(String token){
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        String username = String.valueOf(claims.get("username"));
        JwtTokenClaims jwtTokenClaims = new JwtTokenClaims();
        jwtTokenClaims.setUsername(username);
        return jwtTokenClaims;
    }
}
