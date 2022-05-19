package com.bookross.mainservice.demo.repository;

import com.bookross.mainservice.demo.entity.Blog;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends BaseRepository<Blog, Long> {

}
