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
import java.util.stream.Collectors;

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
    public List<BlogDto> findBlogsByUserID(Long userID) {
        List<Blog> blogs =  getRepository().findBlogsByUserID(userID);
        return blogs.stream().map(this::convertToBlogDto).collect(Collectors.toList());
    }

    public BlogDto convertToBlogDto(Blog blog){
        BlogDto blogDto = new BlogDto();
        blogDto.setId(blog.getId());
        blogDto.setUserID(blog.getUser().getId());
        blogDto.setTopic(blog.getTopic());
        blogDto.setDateOfPublication(blog.getDateOfPublication());
        blogDto.setBlogText(blog.getBlogText());
        return blogDto;
    }

    @Override
    public BlogDto findBlog(Long id) {
        Blog blog = findOrThrowNotFound(id);
        return convertToBlogDto(blog);
    }

    @Override
    public void deleteBlog(Long id) {
        getRepository().delete(findOrThrowNotFound(id));
    }

    @Override
    public void updateBlog(BlogDto blogDto) {
        Blog blog = findOrThrowNotFound(blogDto.getId());
        if (blogDto.getTopic() != null){
            blog.setTopic(blogDto.getTopic());
        }
        if (blogDto.getBlogText() != null){
            blog.setBlogText(blogDto.getBlogText());
        }
        getRepository().save(blog);
    }
}
