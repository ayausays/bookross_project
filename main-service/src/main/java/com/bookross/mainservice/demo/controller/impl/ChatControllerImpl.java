package com.bookross.mainservice.demo.controller.impl;

import com.bookross.mainservice.demo.controller.ChatController;
import com.bookross.mainservice.demo.entity.request.MessagePostResource;
import com.bookross.mainservice.demo.entity.response.BuddyListGetResources;
import com.bookross.mainservice.demo.entity.response.GetMessageResponse;
import com.bookross.mainservice.demo.service.interfaces.BuddyListService;
import com.bookross.mainservice.demo.service.interfaces.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatControllerImpl implements ChatController {
    private final BuddyListService buddyListService;
    private final MessageService messageService;

    @Override
    public ResponseEntity<Page<BuddyListGetResources>> getBuddyList(int pageIndex,String userId) {

        return ResponseEntity.ok(buddyListService.getBuddyList(pageIndex,Long.parseLong(userId)));
    }

    @Override
    public ResponseEntity<GetMessageResponse> sendMessage(Long receiverId,Long me, MessagePostResource messagePostResource) {
        try {
            Long senderId = buddyListService.getASenderId(receiverId,me);
            return ResponseEntity.status(HttpStatus.CREATED).body(messageService.sendAMessage(messagePostResource, senderId,me));
        } catch (Exception e) {
//          throw new OperationFailed(e.getLocalizedMessage());
            throw new RuntimeException(e.getLocalizedMessage()); //change later with design
        }
    }



    @Override
    public ResponseEntity<Page<GetMessageResponse>> getAUserMessages(String userId,int page,Long me) {
        try {
            return ResponseEntity.ok(messageService.getAUserMessages(Long.parseLong(userId),page,me));
        } catch (Exception e) {
//          throw new OperationFailed(e.getLocalizedMessage());
            throw new RuntimeException(e.getLocalizedMessage()); //change later with design
        }
    }
}
