package com.bookross.mainservice.demo.service.interfaces;

import com.bookross.mainservice.demo.entity.AppUser;
import com.bookross.mainservice.demo.entity.request.AppUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface AppUserService extends BaseService<AppUser, Long> {
    List<AppUser> getAllUsers();
    AppUser getUserById(Long id);
    void updateAppUser(Long userID, String firstName, String lastName, String email);
    void deleteUser(Long id);
    AppUser findByEmail(String email);
    AppUserDto getUserDtoByEmail(String email);
    AppUserDto getUserDtoById(Long id);
    List<AppUserDto> getAllUserDtos();
    List<AppUserDto> searchUsers(String value);
}
