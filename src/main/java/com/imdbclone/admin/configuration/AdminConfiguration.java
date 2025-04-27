package com.imdbclone.admin.configuration;

import com.imdbclone.admin.repository.AdminRepository;
import com.imdbclone.admin.service.api.IAdminService;
import com.imdbclone.admin.service.api.IJWTService;
import com.imdbclone.admin.service.implementation.AdminServiceImpl;
import com.imdbclone.admin.service.implementation.JWTServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AdminConfiguration {

    @Bean
    public IAdminService adminService(AdminRepository adminRepository){
        return new AdminServiceImpl(adminRepository);
    }

}
