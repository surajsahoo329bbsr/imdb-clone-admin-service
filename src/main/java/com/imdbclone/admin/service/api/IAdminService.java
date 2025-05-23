package com.imdbclone.admin.service.api;

import com.imdbclone.admin.web.response.UserDTO;

import java.util.List;

public interface IAdminService {

    String login(String username, String email, String password);

    List<UserDTO> getAllUsers(Integer pageNumber, Integer pageSize, boolean sortByLatestFirst);

    List<UserDTO> getAllSoftDeletedUsers(Integer pageNumber, Integer pageSize, boolean sortByLatestFirst);

    void hardDeleteUserById(Long userId);
}
