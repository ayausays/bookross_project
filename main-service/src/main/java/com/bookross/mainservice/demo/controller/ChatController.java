package com.bookross.mainservice.demo.controller;
import com.bookross.mainservice.demo.entity.request.MessagePostResource;
import com.bookross.mainservice.demo.entity.response.BuddyListGetResources;
import com.bookross.mainservice.demo.entity.response.GetMessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/message")
@CrossOrigin("*")
public interface ChatController {

    @GetMapping("/buddylist/{pageIndex}/{me}")
    public ResponseEntity<Page<BuddyListGetResources>> getBuddyList(@PathVariable("pageIndex") int pageIndex,@PathVariable("me") String me);


    @PostMapping("/send/{id}/{me}")
    public ResponseEntity<GetMessageResponse> sendMessage(@PathVariable("id") Long receiverId,
                                                          @PathVariable("me") Long me,
                                                          @RequestBody MessagePostResource messagePostResource
    );

    @GetMapping("/get/{userId}/{page}/{me}")
    @SendTo("/topic/greeting")
    public ResponseEntity<Page<GetMessageResponse>> getAUserMessages(@PathVariable String userId, @PathVariable int page,@PathVariable Long me);

}
