package com.example.danggunmarket.common.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final long expireTime = 10000;
    @Value("${jwt.secret.key}")
    private String secretKey;

    public String createToken(String email) {
        Claims claims = Jwts.claims()
                .add("email", email)
                .build();

        return Jwts.builder()
                .claims(claims)
                .issuedAt(new Date())
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .expiration(new Date(System.currentTimeMillis() + expireTime))
                .subject("danggunmarket")
                .compact();
    }

    public Claims parse(String token) {
        try {

            return (Claims) Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build()
                    .parse(token)
                    .getPayload();
            
        } catch (ExpiredJwtException e) {
            throw new JwtException("유효 시간이 만료된 토큰입니다.");
        } catch (MalformedJwtException e) {
            throw new JwtException("올바르지 않은 형식의 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            throw new JwtException("지원하지 않는 토큰입니다.");
        }catch (PrematureJwtException ex){
            throw new JwtException("승인되지 않은 토큰입니다.");
        }
    }
}
