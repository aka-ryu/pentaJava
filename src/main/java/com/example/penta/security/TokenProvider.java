package com.example.penta.security;

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

    // 토큰생성
    public String create(String content) {
        Date expiryDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));

        return Jwts.builder()
                .setIssuer("penta")
                .setIssuedAt(new Date())
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(60).toInstant()))
                .claim("sub", content)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    // requset 시 토큰 확인
    public String validateAndGetUserId(String token) {
        String tokenValue = null;

        try{
            DefaultJws defaultJws = (DefaultJws) Jwts.parser()
                    .setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token);

            DefaultClaims defaultClaims = (DefaultClaims) defaultJws.getBody();

            tokenValue = defaultClaims.getSubject();

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            tokenValue = null;
        }
        return tokenValue;
    }

    public String getTokenBody(String token) {

        String tokenBody;

        try {
            String[] getTokenArray = token.split("\\.");
            tokenBody = getTokenArray[1];

        } catch (Exception e) {
            log.error(e.getMessage());
            tokenBody = null;
        }
        return tokenBody;
    }


}
