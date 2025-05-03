package com.imdbclone.admin.web.controller;

import com.imdbclone.admin.aspect.annotation.SetRequestAttributes;
import com.imdbclone.admin.service.api.IAdminService;
import com.imdbclone.admin.web.response.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin")
@Tag(name = "User Management", description = "APIs for managing users, including login, listing users, and deleting users")
public class AdminController {

    private final IAdminService adminService;

    @Autowired
    public AdminController(IAdminService adminService){
        this.adminService = adminService;
    }

    @SetRequestAttributes
    @GetMapping(path ="/login")
    @Operation(
            summary = "Login user",
            description = "Authenticate a user using username, email, and password. Returns a JWT token or session token on successful login."
    )
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String username, @RequestParam String password) {
        String jwt = adminService.login(email, username, password);
        return new ResponseEntity<>(jwt, HttpStatus.ACCEPTED);
    }

    @SetRequestAttributes
    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Get all active users",
            description = "Fetches a paginated list of all active (non-deleted) users. Supports sorting by latest created first or oldest first."
    )
    public ResponseEntity<?> getAllUsers(Integer pageNumber, Integer pageSize, boolean sortByLatestFirst) {
        List<UserDTO> users = adminService.getAllUsers(pageNumber, pageSize, sortByLatestFirst);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @SetRequestAttributes
    @GetMapping(path = "/users/deleted", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Get all soft-deleted users",
            description = "Fetches a paginated list of users that have been soft-deleted. Supports sorting by latest deleted first or oldest first."
    )
    public ResponseEntity<?> getAllSoftDeletedUsers(Integer pageNumber, Integer pageSize, boolean sortByLatestFirst) {
        List<UserDTO> softDeletedUsers = adminService.getAllSoftDeletedUsers(pageNumber, pageSize, sortByLatestFirst);
        return new ResponseEntity<>(softDeletedUsers, HttpStatus.OK);
    }

    @SetRequestAttributes
    @PostMapping(path ="/users/delete")
    @Operation(
            summary = "Hard delete user by ID",
            description = "Permanently deletes a user by their ID. This operation cannot be undone and will remove the user from the database."
    )
    public ResponseEntity<?> hardDeleteUserById(Long userId) {
        adminService.hardDeleteUserById(userId);
        return new ResponseEntity<>(userId, HttpStatus.ACCEPTED);
    }

}
