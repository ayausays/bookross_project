package com.bookross.mainservice.demo.service.interfaces;

import com.bookross.mainservice.demo.entity.request.MessagePostResource;
import com.bookross.mainservice.demo.entity.response.GetMessageResponse;
import org.springframework.data.domain.Page;

public interface MessageService {
    GetMessageResponse sendAMessage(MessagePostResource messagePostResource, Long senderId,Long me);

    Page<GetMessageResponse> getAUserMessages(long parseLong, int page,Long me);
}
