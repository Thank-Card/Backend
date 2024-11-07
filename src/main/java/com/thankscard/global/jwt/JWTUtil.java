package com.thankscard.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

    private final SecretKey secretKey;

    public JWTUtil(@Value("${spring.jwt.secret}") String secret) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getLoginId(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("loginId", String.class);
    }

    public String getRole(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public Boolean isExpired(String token) {
        if (token == null || token.trim().isEmpty()) {
            return false;
        }
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public Boolean isValid(String accessToken, String refreshToken) {

        if (accessToken == null || accessToken.trim().isEmpty()) {
            return false;
        }
        if (refreshToken == null || refreshToken.trim().isEmpty()) {
            return false;
        }

        try {
            // AccessToken과 RefreshToken의 Claims 추출
            Claims accessTokenClaims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(accessToken)
                    .getPayload();

            Claims refreshTokenClaims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(refreshToken)
                    .getPayload();

            // 발급 시간 비교
            return !accessTokenClaims.getIssuedAt().before(refreshTokenClaims.getIssuedAt());
        } catch (Exception e) {
            // 예외 발생 시 유효하지 않다고 판단
            throw new JwtException("Invalid JWT");
        }
    }

    public String createJwt(String loginId, String role, Long expiredMs) {

        return Jwts.builder()
                .claim("loginId", loginId)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }
}
