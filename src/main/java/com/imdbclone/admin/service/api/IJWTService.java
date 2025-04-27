package com.imdbclone.admin.service.api;

public interface IJWTService {

    String generateToken(Long adminId, String username, String role);

}
