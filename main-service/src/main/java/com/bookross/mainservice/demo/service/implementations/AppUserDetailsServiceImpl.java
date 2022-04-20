package com.bookross.mainservice.demo.service.implementations;

import com.bookross.mainservice.demo.entity.AppUser;
import com.bookross.mainservice.demo.entity.AppUserDetails;
import com.bookross.mainservice.demo.repository.AppUserDetailsRepository;
import com.bookross.mainservice.demo.service.interfaces.AppUserDetailsService;
import com.bookross.mainservice.demo.service.interfaces.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AppUserDetailsServiceImpl  extends BaseServiceImpl<AppUserDetails, Long, AppUserDetailsRepository>
        implements AppUserDetailsService {

    private final AppUserService appUserService;

    @Override
    public void updateAppUserDetails(Long userID, String city, LocalDate dob, String phoneNumber, String aboutUser){
        AppUser user = appUserService.findOrThrowNotFound(userID);
        AppUserDetails details = user.getAppUserDetails();
        if (user.getAppUserDetails() == null){
            details = new AppUserDetails();
        }
        if (city != null)
            details.setCity(city);
        if (dob != null)
            details.setDob(dob);
        if (phoneNumber != null)
            details.setPhoneNumber(phoneNumber);
        if (aboutUser != null)
            details.setAboutUser(aboutUser);
        AppUserDetails newDetails = getRepository().save(details);
        user.setAppUserDetails(newDetails);
        appUserService.save(user);
    }

}
