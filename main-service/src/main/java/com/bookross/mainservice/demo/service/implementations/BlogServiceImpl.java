package com.bookross.mainservice.demo.service.implementations;


import com.bookross.mainservice.demo.entity.AppUser;
import com.bookross.mainservice.demo.entity.Blog;
import com.bookross.mainservice.demo.entity.request.BlogDto;
import com.bookross.mainservice.demo.repository.BlogRepository;
import com.bookross.mainservice.demo.service.interfaces.AppUserService;
import com.bookross.mainservice.demo.service.interfaces.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl extends BaseServiceImpl<Blog, Long, BlogRepository>
                            implements BlogService {
    private final AppUserService appUserService;

    @Override
    public void saveBlog(BlogDto blogDto) {
        Blog blog = new Blog();
        blog.setBlogText(blogDto.getBlogText());
        blog.setTopic(blogDto.getTopic());
        blog.setDateOfPublication(LocalDateTime.now());
        AppUser appUser = appUserService.findOrThrowNotFound(blogDto.getUserID());
        blog.setUser(appUser);
        getRepository().save(blog);
    }

    @Override
    public List<Blog> findBlogsByUserID(Long userID) {
        return getRepository().findBlogsByUserID(userID);
    }

    @Override
    public Blog findBlog(Long id) {
        return findOrThrowNotFound(id);
    }

    @Override
    public void deleteBlog(Long id) {
        getRepository().delete(findOrThrowNotFound(id));
    }

    @Override
    public void updateBlog(Long id, String topic, String blogText) {
        Blog blog = findOrThrowNotFound(id);
        if (topic != null){
            blog.setTopic(topic);
        }
        if (blogText != null){
            blog.setBlogText(blogText);
        }
        getRepository().save(blog);
    }
}
