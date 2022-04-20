package com.bookross.mainservice.demo.service.implementations;

import com.bookross.mainservice.demo.entity.Support;
import com.bookross.mainservice.demo.entity.request.SupportDto;
import com.bookross.mainservice.demo.repository.SupportRepository;
import com.bookross.mainservice.demo.service.interfaces.SupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupportServiceImpl extends BaseServiceImpl<Support, Long, SupportRepository> implements SupportService {

    @Override
    public void saveSupport(SupportDto supportDto) {
        Support support = new Support();
        support.setSupportEmail(supportDto.getSupportEmail());
        support.setSupportType(supportDto.getSupportType());
        support.setSupportComment(supportDto.getSupportComment());
        getRepository().save(support);
    }
}
