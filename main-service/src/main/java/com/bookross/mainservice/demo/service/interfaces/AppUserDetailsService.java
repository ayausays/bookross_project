package com.bookross.mainservice.demo.service.interfaces;
;
import com.bookross.mainservice.demo.entity.AppUserDetails;

import java.time.LocalDate;

public interface AppUserDetailsService extends BaseService<AppUserDetails, Long>  {
    void updateAppUserDetails(Long userID, String city, LocalDate dob, String phoneNumber, String aboutUser);
}
