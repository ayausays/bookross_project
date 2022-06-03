package com.bookross.mainservice.demo.service.implementations;

import com.bookross.mainservice.demo.entity.AppUser;
import com.bookross.mainservice.demo.entity.AppUserDetails;
import com.bookross.mainservice.demo.entity.request.AppUserDto;
import com.bookross.mainservice.demo.exception.EntityNotFoundException;
import com.bookross.mainservice.demo.repository.AppUserRepository;
import com.bookross.mainservice.demo.service.interfaces.AppUserService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUserServiceImpl extends BaseServiceImpl<AppUser, Long, AppUserRepository> implements AppUserService {

    @Override
    public List<AppUser> getAllUsers() {
        return getRepository().findAll();
    }

    @Override
    public AppUser getUserById(Long id) {
        return findOrThrowNotFound(id);
    }

    @Override
    public AppUser findByEmail(String email) {
        boolean exists = getRepository().findByEmail(email).isPresent();
        return exists ? getRepository().findByEmail(email).get() : null;
    }

    @Override
    public AppUserDto getUserDtoByEmail(String email) {
        if (findByEmail(email) == null) {
            throw new EntityNotFoundException(AppUser.class, "User by the given email does not exist", email);
        }
        return convertToAppUserDto(findByEmail(email));
    }

    @Override
    public List<AppUserDto> getAllUserDtos() {
        List<AppUser> appUsers = getAllUsers();
        return appUsers.stream().map(this::convertToAppUserDto).collect(Collectors.toList());
    }

    @Override
    public AppUserDto getUserDtoById(Long id) {
        return convertToAppUserDto(getUserById(id));
    }

    @Override
    public void updateAppUser(Long userID, String firstName, String lastName, String email) {
        AppUser user = findOrThrowNotFound(userID);
        if (firstName != null)
            user.setFirstName(firstName);
        if (lastName != null)
            user.setLastName(lastName);
        if (email != null && findByEmail(email) == null) {
            user.setEmail(email);
        }
        getRepository().save(user);
    }

    @Override
    public void deleteUser(Long id) {
        getRepository().delete(findOrThrowNotFound(id));
    }

    private AppUserDto convertToAppUserDto(AppUser appUser) {
        AppUserDto appUserDto = new AppUserDto();
        AppUserDetails appUserDetails = appUser.getAppUserDetails();
        if (appUserDetails != null) {
            if (appUserDetails.getImagePath() != null) appUserDto.setAppUserImagePath(appUserDetails.getImagePath());
        }
        appUserDto.setId(appUser.getId());
        appUserDto.setEmail(appUser.getEmail());
        appUserDto.setAppUserRole(appUser.getAppUserRole());
        appUserDto.setFirstName(appUser.getFirstName());
        appUserDto.setLastName(appUser.getLastName());
        return appUserDto;
    }

    @Override
    public List<AppUserDto> searchUsers(String value) {
        List<AppUser> allUsers = getAll();
        System.out.println(allUsers.get(1).getFirstName().contains(value.toLowerCase().trim()));
        System.out.println(allUsers.get(1).getLastName().contains(value.toLowerCase().trim()));
        return allUsers.stream()
                .filter(user -> !user.isLocked() && user.isEnabled())
                .filter(user -> (user.getFirstName().toLowerCase().startsWith(value.toLowerCase())
                        || user.getLastName().toLowerCase().startsWith(value.toLowerCase())))
                .map(this::convertToAppUserDto)
                .sorted(Comparator.comparing(AppUserDto::getFirstName))
                .collect(Collectors.toList());
    }

}
