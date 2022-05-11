package com.bookross.mainservice.demo.repository;

import com.bookross.mainservice.demo.entity.City;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends BaseRepository<City, Long> {
}
