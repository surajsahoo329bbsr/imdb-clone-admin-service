package com.imdbclone.admin.service.implementation;

import com.imdbclone.admin.service.api.IJWTService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Primary
public class JWTServiceImpl implements IJWTService {

    private final PasswordEncoder passwordEncoder;
    private final String jwtSecretKey;
    private final String username;
    private final String email;
    private final String hashedPassword;


    public JWTServiceImpl(PasswordEncoder passwordEncoder, String jwtSecretKey, String username, String email, String hashedPassword) {
        this.passwordEncoder = passwordEncoder;
        this.jwtSecretKey = jwtSecretKey;
        this.username = username;
        this.email = email;
        this.hashedPassword = hashedPassword;
    }

    @Override
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

    /*@Override
    public String verifyAdminCredentialsToGenerateToken(String username, String email, String password) {
        Long adminId = adminServiceClient.authenticateAdminAndFetchId(username, email, password);
        return adminId != null ? generateToken(adminId, username, "ADMIN") : "Invalid Admin Credentials";
    }*/
}

