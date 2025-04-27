package com.imdbclone.admin.configuration;

import com.imdbclone.admin.repository.AdminRepository;
import com.imdbclone.admin.service.api.IAdminService;
import com.imdbclone.admin.service.implementation.AdminServiceImpl;
import com.imdbclone.admin.util.JWTUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminConfiguration {

    @Value("${spring.jwt.secret}")
    private String jwtSecretKey;

    @Bean
    public IAdminService adminService(AdminRepository adminRepository, JWTUtils jwtUtils, PasswordEncoder passwordEncoder){
        return new AdminServiceImpl(adminRepository, jwtUtils, passwordEncoder);
    }

    @Bean
    public JWTUtils jwtUtils() {
        return new JWTUtils(jwtSecretKey);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
