package com.imdbclone.admin.service.api;

import com.imdbclone.admin.web.response.UserDTO;

import java.util.List;

public interface IAdminService {

    void login(String username, String email, String password);

    List<UserDTO> getAllUsers(Integer pageNumber, Integer pageSize, boolean sortByLatestFirst);

    void deleteUserById(Long userId);
}
