package com.bookross.mainservice.demo.service.interfaces;

import com.bookross.mainservice.demo.entity.response.BuddyListGetResources;
import org.springframework.data.domain.Page;

public interface BuddyListService {
    /*
this just give the list
when This user has buddylist with isRead false
isRead will false when sender send message but receiver did not read it .
receiver is current user which i get from token and UserContextHolder
*/
    public Long getHanggedMessageNumber();

    /*
    It will returns all buddy list which is active
    */
    public Page<BuddyListGetResources> getBuddyList(int pageIndex);

    /*

     */
    public Long getASenderId(Long receiver,Long me);
}
