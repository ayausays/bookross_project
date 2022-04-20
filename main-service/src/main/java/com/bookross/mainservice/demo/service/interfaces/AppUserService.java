package com.bookross.mainservice.demo.service.interfaces;

import com.bookross.mainservice.demo.entity.AppUser;

import java.util.List;
import java.util.Optional;


public interface AppUserService extends BaseService<AppUser, Long> {
    List<AppUser> getAllUsers();
    AppUser getUserById(Long id);
    void updateAppUser(Long userID, String firstName, String lastName, String email);
    void deleteUser(Long id);
    Optional<AppUser> findByEmail(String email);
}
