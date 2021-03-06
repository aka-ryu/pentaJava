package com.example.penta.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.DefaultJws;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Log4j2
@Service
public class TokenProvider {

    
    //비밀키
    private static final String SECRET_KEY = "AJB3BUDS882JSAHSHH2JS";

    // Access 토큰생성
    public String accessToken(String content) {
        Date expiryDate = Date.from(Instant.now().plus(1, ChronoUnit.MINUTES));

        return Jwts.builder()
                // header에 들어갈 내용 및 서명을 하기 위한 SECRET_KEY
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                // payload에 들어갈 내용
                .setSubject(content) // sub
                .setIssuer("penta") // iss
                .setIssuedAt(new Date()) // iat
                .setExpiration(expiryDate) // exp
                .compact();
    }

     // Refresh 토큰생성
    public String refreshToken(String content) {
        Date expiryDate = Date.from(Instant.now().plus(7, ChronoUnit.DAYS));

        return Jwts.builder()
                // header에 들어갈 내용 및 서명을 하기 위한 SECRET_KEY
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                // payload에 들어갈 내용
                .setSubject(content+"refresh") // sub
                .setIssuer("penta") // iss
                .setIssuedAt(new Date()) // iat
                .setExpiration(expiryDate) // exp
                .compact();
    }


    // requset 시 토큰 확인
    public String validateAndGetUserId(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }



}
