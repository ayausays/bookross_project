package com.bookross.mainservice.demo.service.interfaces;

import com.bookross.mainservice.demo.entity.Blog;
import com.bookross.mainservice.demo.entity.request.BlogDto;

import java.util.List;

public interface BlogService extends BaseService<Blog, Long>{
    void saveBlog(BlogDto BlogDto);
    List<BlogDto> findBlogsByUserID(Long userID);
    BlogDto findBlog(Long id);
    void deleteBlog(Long id);
    void updateBlog(BlogDto blogDto);
    void addBlogToUserFavs(Long userID, Long blogID);
    void deleteBlogFromUserFavs(Long userID, Long blogID);
    List<BlogDto> getUserFavBlogs(Long userID);
}
