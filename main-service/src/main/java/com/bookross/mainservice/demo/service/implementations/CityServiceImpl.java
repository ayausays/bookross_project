package com.bookross.mainservice.demo.service.implementations;

import com.bookross.mainservice.demo.entity.City;
import com.bookross.mainservice.demo.repository.CityRepository;
import com.bookross.mainservice.demo.service.interfaces.CityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl extends BaseServiceImpl<City, Long, CityRepository>
        implements CityService {
    @Override
    public List<String> getAllCities() {
        return getRepository().findAll().stream().map(City::getCity).collect(Collectors.toList());
    }
}
