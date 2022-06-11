package com.bookross.mainservice.demo.service.interfaces;

import com.bookross.mainservice.demo.entity.response.BuddyListGetResources;
import org.springframework.data.domain.Page;

public interface BuddyListService {

    public Long getHanggedMessageNumber();

    /*
    It will returns all buddy list which is active
    */
    public Page<BuddyListGetResources> getBuddyList(int pageIndex,Long userId);

    public Long getASenderId(Long receiver,Long me);
}
