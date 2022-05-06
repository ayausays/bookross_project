package com.bookross.mainservice.demo.service.interfaces;

import com.bookross.mainservice.demo.entity.Blog;
import com.bookross.mainservice.demo.entity.request.BlogDto;

import java.util.List;

public interface BlogService extends BaseService<Blog, Long>{
    void saveBlog(BlogDto BlogDto);
    List<Blog> findBlogsByUserID(Long userID);
    Blog findBlog(Long id);
    void deleteBlog(Long id);
    void updateBlog(Long id, String topic, String blogText);
}
