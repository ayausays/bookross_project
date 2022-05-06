package com.bookross.mainservice.demo.service.implementations;

import com.bookross.mainservice.demo.entity.AppUser;
import com.bookross.mainservice.demo.repository.AppUserRepository;
import com.bookross.mainservice.demo.service.interfaces.AppUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl extends BaseServiceImpl<AppUser, Long, AppUserRepository> implements AppUserService {

    @Override
    public void updateAppUser(Long userID, String firstName, String lastName, String email) {
        AppUser user = findOrThrowNotFound(userID);
        if (firstName != null)
            user.setFirstName(firstName);
        if (lastName != null)
            user.setLastName(lastName);
        if (email != null && !findByEmail(email).isPresent()){
            user.setEmail(email);
        }
        getRepository().save(user);
    }

    @Override
    public void deleteUser(Long id) {
        findOrThrowNotFound(id);
        getRepository().deleteById(id);
    }

    @Override
    public Optional<AppUser> findByEmail(String email) {
        return getRepository().findByEmail(email);
    }

    @Override
    public List<AppUser> getAllUsers() {
        return getRepository().findAll();
    }

    @Override
    public AppUser getUserById(Long id) {
        return findOrThrowNotFound(id);
    }

}
