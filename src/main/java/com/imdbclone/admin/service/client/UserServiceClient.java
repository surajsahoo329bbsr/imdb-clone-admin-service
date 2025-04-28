package com.imdbclone.admin.service.client;

import com.imdbclone.admin.web.response.UserDTO;

import java.util.List;

public interface UserServiceClient {

    //@GetMapping(path = "/cast") //TODO path
    List<UserDTO> getAllUsers(Integer pageNumber, Integer pageSize, boolean sortByLatestFirst);

    List<UserDTO> getAllSoftDeletedUsers(Integer pageNumber, Integer pageSize, boolean sortByLatestFirst);

    //Hard Delete User
    void hardDeleteUserById(Long userId);
}
