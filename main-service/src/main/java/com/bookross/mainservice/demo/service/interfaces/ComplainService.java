package com.bookross.mainservice.demo.service.interfaces;

import com.bookross.mainservice.demo.entity.Complain;
import com.bookross.mainservice.demo.entity.request.ComplainDto;

public interface ComplainService extends BaseService<Complain, Long> {
    void saveComplain(ComplainDto complainDto);
}