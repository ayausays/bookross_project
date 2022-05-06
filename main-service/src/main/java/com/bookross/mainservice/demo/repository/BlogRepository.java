package com.bookross.mainservice.demo.repository;

import com.bookross.mainservice.demo.entity.Blog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends BaseRepository<Blog, Long>{

    @Query("select b from Blog b where b.user.id = ?1")
    List<Blog> findBlogsByUserID(Long userID);
}
