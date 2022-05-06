package com.bookross.mainservice.demo.controller;


import com.bookross.mainservice.demo.entity.Blog;
import com.bookross.mainservice.demo.entity.request.BlogDto;
import com.bookross.mainservice.demo.service.interfaces.BlogService;
import com.bookross.mainservice.demo.service.interfaces.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/userBlogs")
@RequiredArgsConstructor
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1920 * 1080 * 50, maxRequestSize = 1920 * 1080 * 100)
public class BlogController {

    private final ImageService imageService;
    private final BlogService blogService;

    // todo: make dtos for all entities to return them for get requests

    @GetMapping(path = "/getUserBlogs/{userID}")
    public ResponseEntity<List<Blog>> getUserBlogsById(@PathVariable("userID") Long id) {
        return ResponseEntity.ok(blogService.findBlogsByUserID(id));
    }
    
    @GetMapping(path = "getBlog/{blogID}")
    public ResponseEntity<Blog> getBlog(@PathVariable("blogID") Long id){
        return ResponseEntity.ok(blogService.findBlog(id));
    }
    
    @PostMapping(path = "/addBlog")
    public ResponseEntity<Void> addBlog(@RequestBody BlogDto blogDto){
        blogService.saveBlog(blogDto);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping(path = "/updateBlog/{blogID}")
    public ResponseEntity<Void> updateBlog(@PathVariable("blogID") Long id, 
                                           @RequestParam(required = false) String topic,
                                           @RequestParam(required = false) String blogText){
        blogService.updateBlog(id, topic, blogText);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/updateBlogImage/{blogID}")
    public ResponseEntity<Void> updateBlogImage(@PathVariable("blogID") Long id, @RequestPart("image") MultipartFile file){
        imageService.saveBlogImage(id, file);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/deleteBlog/{blogID}")
    public ResponseEntity<Void> deleteBlog(@PathVariable("blogID") Long id){
        blogService.deleteBlog(id);
        return ResponseEntity.ok().build();
    }

}
