package com.imdbclone.admin.configuration;

import com.imdbclone.admin.filter.JWTAuthFilter;
import com.imdbclone.admin.repository.AdminRepository;
import com.imdbclone.admin.repository.GenreRepository;
import com.imdbclone.admin.service.api.IAdminService;
import com.imdbclone.admin.service.api.IGenreService;
import com.imdbclone.admin.service.client.UserServiceClient;
import com.imdbclone.admin.service.implementation.AdminServiceImpl;
import com.imdbclone.admin.service.implementation.GenreServiceImpl;
import com.imdbclone.admin.service.implementation.UserServiceClientImpl;
import com.imdbclone.admin.util.JWTUtils;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public IAdminService adminService(AdminRepository adminRepository, JWTUtils jwtUtils, PasswordEncoder passwordEncoder, @Qualifier("userServiceClientImpl") UserServiceClient userServiceClient) {
        return new AdminServiceImpl(adminRepository, jwtUtils, passwordEncoder, userServiceClient);
    }

    @Bean
    public JWTUtils jwtUtils() {
        return new JWTUtils(jwtSecretKey);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserServiceClient userServiceClient() {
        return new UserServiceClientImpl();
    }

    @Bean
    public IGenreService genreService(GenreRepository genreRepository, AdminRepository adminRepository, JWTUtils jwtUtils) {
        return new GenreServiceImpl(genreRepository, adminRepository, jwtUtils);
    }

    @Bean
    public JWTAuthFilter jwtAuthFilter() {
        return new JWTAuthFilter(jwtSecretKey);
    }

}
