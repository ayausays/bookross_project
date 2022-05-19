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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

        appUser.getBlogs().add(blog);
        appUserService.save(appUser);
    }

    @Override
    public List<BlogDto> findBlogsByUserID(Long userID) {
        AppUser appUser = appUserService.findOrThrowNotFound(userID);
        List<BlogDto> blogDtos = new ArrayList<>();
        appUser.getBlogs().forEach(blog -> {
            blogDtos.add(convertToBlogDto(blog));
        });
        return blogDtos;
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
        Blog blog = findOrThrowNotFound(id);
        blog.getUsersAddedToFavs().forEach(user -> {
            Set<Blog> blogs = user.getFavoriteBlogs();
            blogs.remove(blog);
            user.setFavoriteBlogs(blogs);
            appUserService.save(user);
        });
        getRepository().delete(blog);
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

    @Override
    public void addBlogToUserFavs(Long userID, Long blogID) {
        AppUser appUser = appUserService.findOrThrowNotFound(userID);
        Blog blog = findOrThrowNotFound(blogID);
        Set<Blog> blogs = appUser.getFavoriteBlogs();
        blogs.add(blog);
        appUser.setFavoriteBlogs(blogs);

        Set<AppUser> users = blog.getUsersAddedToFavs();
        users.add(appUser);
        blog.setUsersAddedToFavs(users);

        appUserService.save(appUser);
        save(blog);
    }

    @Override
    public void deleteBlogFromUserFavs(Long userID, Long blogID) {
        AppUser appUser = appUserService.findOrThrowNotFound(userID);
        Blog blog = findOrThrowNotFound(blogID);
        Set<Blog> blogs = appUser.getFavoriteBlogs();
        blogs.remove(blog);
        appUser.setFavoriteBlogs(blogs);

        Set<AppUser> users = blog.getUsersAddedToFavs();
        users.remove(appUser);
        blog.setUsersAddedToFavs(users);

        appUserService.save(appUser);
        save(blog);
    }

    @Override
    public List<BlogDto> getUserFavBlogs(Long userID) {
        AppUser appUser = appUserService.findOrThrowNotFound(userID);
        List<BlogDto> blogDtos = new ArrayList<>();
        appUser.getFavoriteBlogs()
                .forEach(blog -> {
                    BlogDto blogDto = convertToBlogDto(blog);
                    blogDtos.add(blogDto);
                });
        return blogDtos;
    }
}
