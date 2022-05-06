package com.bookross.mainservice.demo.service.interfaces;

import com.bookross.mainservice.demo.entity.Support;
import com.bookross.mainservice.demo.entity.request.SupportDto;

public interface SupportService extends BaseService<Support, Long> {
    void saveSupport(SupportDto supportDto);
}
