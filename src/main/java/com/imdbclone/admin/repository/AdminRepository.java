package com.imdbclone.admin.repository;

import com.imdbclone.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("SELECT a FROM Admin a WHERE a.email = :email OR a.username = :username")
    Admin findByUsernameOrEmail(@Param("email") String email, @Param("username") String username);

}
