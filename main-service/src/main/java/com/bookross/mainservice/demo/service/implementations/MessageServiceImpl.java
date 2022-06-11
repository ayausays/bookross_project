package com.bookross.mainservice.demo.service.implementations;

import com.bookross.mainservice.demo.entity.Message;
import com.bookross.mainservice.demo.entity.request.MessagePostResource;
import com.bookross.mainservice.demo.entity.response.GetMessageResponse;
import com.bookross.mainservice.demo.repository.MessageRepository;
import com.bookross.mainservice.demo.service.interfaces.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public GetMessageResponse sendAMessage(MessagePostResource messagePostResource, Long senderId,Long me) {
        Message message = new Message();
        message.setCommunicationId(senderId);
        message.setMessage(messagePostResource.getMessageBody());

        message = messageRepository.save(message);
        GetMessageResponse response =GetMessageResponse.builder()
                .messageId(message.getId())
                .userId(me)//loged userid
                .message(message.getMessage())
                .createdAt(message.getCreatedAt())
                .build();

        return response;
    }

    @Override
    public Page<GetMessageResponse> getAUserMessages(long buddyId, int page,Long me) {
        Pageable pageable = PageRequest.of(page, 20);

        return messageRepository.getAUserMessages(buddyId, me, pageable);
    }
}
