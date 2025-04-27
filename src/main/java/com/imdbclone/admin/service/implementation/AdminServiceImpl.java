package com.imdbclone.admin.service.implementation;

import com.imdbclone.admin.repository.AdminRepository;
import com.imdbclone.admin.service.api.IAdminService;
import com.imdbclone.admin.service.api.IJWTService;
import com.imdbclone.admin.web.response.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AdminServiceImpl implements IAdminService {

    private final AdminRepository adminRepository;
    //private IJWTService jwtService;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void login(String username, String email, String password) {

    }

    @Override
    public List<UserDTO> getAllUsers(Integer pageNumber, Integer pageSize, boolean sortByLatestFirst) {
        return List.of();
    }

    @Override
    public void deleteUserById(Long userId) {

    }
}
