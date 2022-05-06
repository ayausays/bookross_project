package com.bookross.mainservice.demo.service.implementations;

import com.bookross.mainservice.demo.entity.Complain;
import com.bookross.mainservice.demo.entity.request.ComplainDto;
import com.bookross.mainservice.demo.repository.ComplainRepository;
import com.bookross.mainservice.demo.service.interfaces.ComplainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComplainServiceImpl extends BaseServiceImpl<Complain, Long, ComplainRepository> implements ComplainService {

    @Override
    public void saveComplain(ComplainDto complainDto) {
        Complain complain = new Complain();
        complain.setLoginOfUser(complainDto.getLoginOfUser());
        complain.setComplainType(complainDto.getComplainType());
        complain.setComplainComment(complainDto.getComplainComment());
        getRepository().save(complain);
    }
}
