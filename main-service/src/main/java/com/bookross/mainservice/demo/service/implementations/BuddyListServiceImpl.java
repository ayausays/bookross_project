package com.bookross.mainservice.demo.service.implementations;

import com.bookross.mainservice.demo.entity.AppUser;
import com.bookross.mainservice.demo.entity.BuddyList;
import com.bookross.mainservice.demo.entity.response.BuddyListGetResources;
import com.bookross.mainservice.demo.repository.AppUserRepository;
import com.bookross.mainservice.demo.repository.BuddylistRepository;
import com.bookross.mainservice.demo.service.interfaces.BuddyListService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class BuddyListServiceImpl implements BuddyListService {
    private Logger log = Logger.getLogger(BuddyListServiceImpl.class.getName());
    private final BuddylistRepository buddylistRepository;
    private final AppUserRepository appUserRepository;

    @Override
    public Page<BuddyListGetResources> getBuddyList(int pageIndex,Long userId) {

        Pageable pageable= PageRequest.of(pageIndex,20);
        Page<BuddyListGetResources> buddyList = buddylistRepository.findByReceiverId(userId,pageable);

        return buddyList;

    }

    @Override
    public Long getHanggedMessageNumber() {
        try {
            Long userId=null;
            return buddylistRepository.countByReceiverIdAndIsRead(userId, false);
        } catch (Exception e) {
            throw  new RuntimeException(e.getLocalizedMessage());
        }

    }

    @Override
    public Long getASenderId(Long receiver,Long me) {
        Long userid = me;
        if (receiver == userid) {
            throw  new RuntimeException("You can't message your self");
        }
        Optional<BuddyList> getBuddy = buddylistRepository.findBySenderIdAndReceiverId(receiver, userid);
        if (getBuddy.isPresent()) {
            BuddyList buddylist = getBuddy.get();

            if(buddylist.getStatus()!=1){
                throw new RuntimeException("You're blocked");
            }

            if (buddylist.getLink() != null) {
                return buddylist.getLink();
            } else {
                try {
                    buddylistRepository.deleteBySenderIdAndReceiverId(userid, receiver);
                    Optional<AppUser> receiveUser = appUserRepository.findById(receiver);
                    if (receiveUser.isPresent()) {

                        BuddyList receiverBuddy=BuddyList.builder()
                                .senderId(me)
                                .receiverId(receiver)
                                .status((byte) 1)
                                .link(buddylist.getId())
                                .isRead(false)
                                .build();



                        receiverBuddy = buddylistRepository.save(receiverBuddy);
                        buddylist.setLink(receiverBuddy.getId());
                        buddylistRepository.save(buddylist);
                        return receiverBuddy.getId();
                    } else {
                        throw new RuntimeException("Receiver's Id is not currect");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e.getLocalizedMessage());
                }

            }
        } else {
            log.info("Called ..... ");
            /*
             * Sender giving first message to receiver
             */

            Optional<BuddyList> getMe = buddylistRepository.findBySenderIdAndReceiverId(userid, receiver);
            if (getMe.isPresent()) {
                return getMe.get().getId();
            } else {
                Optional<AppUser> receiveUser = appUserRepository.findById(receiver);
                if (receiveUser.isPresent()) {

                    BuddyList receiverBuddy=BuddyList.builder()
                            .senderId(me)
                            .receiverId(receiver)
                            .status((byte) 1)
                            .link(null)
                            .isRead(false)
                            .build();

                    receiverBuddy = buddylistRepository.save(receiverBuddy);
                    return receiverBuddy.getId();

                } else {
                    throw new RuntimeException("Receiver's Id is not currect");
                }
            }
        }

    }

}
