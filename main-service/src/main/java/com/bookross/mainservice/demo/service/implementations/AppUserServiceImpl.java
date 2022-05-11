package com.bookross.mainservice.demo.service.implementations;

import com.bookross.mainservice.demo.entity.AppUser;
import com.bookross.mainservice.demo.entity.request.AppUserDto;
import com.bookross.mainservice.demo.exception.EntityNotFoundException;
import com.bookross.mainservice.demo.repository.AppUserRepository;
import com.bookross.mainservice.demo.service.interfaces.AppUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.CascadeType;
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

    // get pageable result
/*    @Override
    public Page<AppUserDto> getAllUserDtos(Pageable pageable) {
        List<AppUser> list = getRepository().getAllUsers(pageable.getPageSize(), pageable.getPageNumber() * pageable.getPageSize());
        List<AppUserDto> appUserDtoList =list.stream().map(this::convertToAppUserDto).collect(Collectors.toList());
        return new PageImpl<>(appUserDtoList, pageable, list.size());
    }*/

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
        if (email != null && findByEmail(email) == null){
            user.setEmail(email);
        }
        getRepository().save(user);
    }

    @Override

    public void deleteUser(Long id) {
        getRepository().delete(findOrThrowNotFound(id));
    }

    private AppUserDto convertToAppUserDto(AppUser appUser){
        AppUserDto appUserDto = new AppUserDto();
        appUserDto.setId(appUser.getId());
        appUserDto.setEmail(appUser.getEmail());
        appUserDto.setAppUserRole(appUser.getAppUserRole());
        appUserDto.setFirstName(appUser.getFirstName());
        appUserDto.setLastName(appUser.getLastName());
        return appUserDto;
    }

}
