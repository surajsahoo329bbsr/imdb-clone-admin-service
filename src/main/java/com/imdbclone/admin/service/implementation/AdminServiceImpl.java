package com.imdbclone.admin.service.implementation;

import com.imdbclone.admin.entity.Admin;
import com.imdbclone.admin.repository.AdminRepository;
import com.imdbclone.admin.service.api.IAdminService;
import com.imdbclone.admin.util.JWTUtils;
import com.imdbclone.admin.web.response.UserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class AdminServiceImpl implements IAdminService {

    private final AdminRepository adminRepository;
    private final JWTUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public AdminServiceImpl(AdminRepository adminRepository, JWTUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(String username, String email, String password) {
        Admin fetchedAdmin = adminRepository.findByUsernameOrEmail(email, username);
        if (fetchedAdmin == null) {
            return "Admin not found with provided username/email";
        }
        if (!passwordEncoder.matches(password, fetchedAdmin.getPasswordHash())) {
            return "Invalid Admin Credentials";
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime sessionExpiryTime = fetchedAdmin.getLastLoginAt().plus(Duration.ofMinutes(60));
        boolean isSessionExpired = now.isAfter(sessionExpiryTime);
        if (!isSessionExpired && fetchedAdmin.isLoggedIn()) {
            return "Admin session is already active. Please logout first.";
        }

        fetchedAdmin.setLastLoginAt(now);
        fetchedAdmin.setLoggedIn(true);
        adminRepository.save(fetchedAdmin);

        return jwtUtils.generateToken(fetchedAdmin.getId(), fetchedAdmin.getUsername(), "ADMIN");
    }

    @Override
    public List<UserDTO> getAllUsers(Integer pageNumber, Integer pageSize, boolean sortByLatestFirst) {
        return List.of();
    }

    @Override
    public void deleteUserById(Long userId) {

    }
}
