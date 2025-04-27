package com.imdbclone.admin.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Slf4j
public class JWTUtils {

    private final String jwtSecretKey;

    public JWTUtils(String jwtSecretKey) {
        this.jwtSecretKey = jwtSecretKey;
    }

    public Long getAdminIdFromJwt() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String jwtToken = null;
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.getCredentials() != null) {
            jwtToken = authentication.getCredentials().toString();
        }

        if (jwtToken != null) {
            return extractAdminIdFromJwt(jwtToken);
        }
        return 0L;
    }

    private Long extractAdminIdFromJwt(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretKey));
            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return Long.valueOf(claims.get("adminId").toString());
        } catch (Exception e) {
            log.error("Error occurred at :{} message :{}", e, e.getMessage());
            return null;
        }
    }

    public String generateToken(Long adminId, String username, String role) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretKey));
        return Jwts.builder()
                .claims(Map.of(
                        "adminId", adminId,
                        "sub", username,
                        "role", role
                ))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1-hour expiry
                .signWith(key)
                .compact();
    }
}
