package com.bookross.mainservice.demo.service.implementations;

import com.bookross.mainservice.demo.entity.AppUser;
import com.bookross.mainservice.demo.entity.AppUserDetails;
import com.bookross.mainservice.demo.entity.request.AppUserDetailsDto;
import com.bookross.mainservice.demo.repository.AppUserDetailsRepository;
import com.bookross.mainservice.demo.service.interfaces.AppUserDetailsService;
import com.bookross.mainservice.demo.service.interfaces.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AppUserDetailsServiceImpl  extends BaseServiceImpl<AppUserDetails, Long, AppUserDetailsRepository>
        implements AppUserDetailsService {

    private final AppUserService appUserService;

    @Override
    public AppUserDetailsDto getAppUserDetails(Long userID) {
        AppUser user = appUserService.findOrThrowNotFound(userID);
        AppUserDetails details = user.getAppUserDetails();
        if (details != null){
            AppUserDetailsDto appUserDetailsDto = new AppUserDetailsDto();
            appUserDetailsDto.setUserID(user.getId());
            appUserDetailsDto.setCity(details.getCity());
            appUserDetailsDto.setPhoneNumber(details.getPhoneNumber());
            appUserDetailsDto.setDob(details.getDob());
            appUserDetailsDto.setAboutUser(details.getAboutUser());
            return appUserDetailsDto;
        }
        return null;
    }

    @Override
    public void saveOrUpdateAppUserDetails(AppUserDetailsDto appUserDetailsDto) {
        AppUser user = appUserService.findOrThrowNotFound(appUserDetailsDto.getUserID());
        AppUserDetails details = user.getAppUserDetails();
        if (user.getAppUserDetails() == null){
            details = new AppUserDetails();
        }
        if (appUserDetailsDto.getCity() != null)
            details.setCity(appUserDetailsDto.getCity());
        if (appUserDetailsDto.getDob() != null)
            details.setDob(appUserDetailsDto.getDob());
        if (appUserDetailsDto.getPhoneNumber() != null)
            details.setPhoneNumber(appUserDetailsDto.getPhoneNumber());
        if (appUserDetailsDto.getAboutUser() != null)
            details.setAboutUser(appUserDetailsDto.getAboutUser());
        AppUserDetails newDetails = getRepository().save(details);
        user.setAppUserDetails(newDetails);
        appUserService.save(user);
    }
}
