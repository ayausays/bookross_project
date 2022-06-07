package com.bookross.mainservice.demo.repository;

import com.bookross.mainservice.demo.entity.Blog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends BaseRepository<Blog, Long> {
    @Query(nativeQuery = true, value ="select b.* from users_fav_blogs u " +
            "left join blog b on u.blog_id = b.id " +
            "group by u.blog_id " +
            "order by count(u.blog_id) desc limit 15;")
    List<Blog> getInterestingBlogs();

}
