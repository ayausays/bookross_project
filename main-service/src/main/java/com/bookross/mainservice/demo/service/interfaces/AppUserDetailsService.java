package com.bookross.mainservice.demo.service.interfaces;
import com.bookross.mainservice.demo.entity.AppUserDetails;
import com.bookross.mainservice.demo.entity.request.AppUserDetailsDto;


public interface AppUserDetailsService extends BaseService<AppUserDetails, Long>  {
    AppUserDetailsDto getAppUserDetails(Long userID);
    void saveOrUpdateAppUserDetails(AppUserDetailsDto appUserDetailsDto);
}
