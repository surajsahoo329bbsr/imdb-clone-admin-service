package com.imdbclone.admin.service.implementation;

import com.imdbclone.admin.service.client.UserServiceClient;
import com.imdbclone.admin.web.response.UserDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("userServiceClientImpl")
public class UserServiceClientImpl implements UserServiceClient {

    @Override
    public List<UserDTO> getAllUsers(Integer pageNumber, Integer pageSize, boolean sortByLatestFirst) {
        return List.of();
    }

    @Override
    public List<UserDTO> getAllSoftDeletedUsers(Integer pageNumber, Integer pageSize, boolean sortByLatestFirst) {
        return List.of();
    }

    @Override
    public void hardDeleteUserById(Long userId) {

    }
}
