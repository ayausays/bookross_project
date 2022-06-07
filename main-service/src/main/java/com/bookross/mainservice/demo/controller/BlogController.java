package com.bookross.mainservice.demo.controller;


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

    @GetMapping(path = "/getUserBlogs/{userID}")
    public ResponseEntity<List<BlogDto>> getUserBlogsById(@PathVariable("userID") Long id) {
        return ResponseEntity.ok(blogService.findBlogsByUserID(id));
    }

    @GetMapping(path = "/getInteresting")
    public ResponseEntity<List<BlogDto>> getInterestingBlogs() {
        return ResponseEntity.ok(blogService.getInterestingBlogs());
    }

    @GetMapping(path = "/getBlog/{blogID}")
    public ResponseEntity<BlogDto> getBlog(@PathVariable("blogID") Long id){
        return ResponseEntity.ok(blogService.findBlog(id));
    }

    @PostMapping(path = "/addBlog")
    public ResponseEntity<Void> addBlog(@RequestBody BlogDto blogDto){
        blogService.saveBlog(blogDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/updateBlog")
    public ResponseEntity<Void> updateBlog(@RequestBody BlogDto blogDto){
        blogService.updateBlog(blogDto);
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

    @GetMapping(path = "/getAllFavs/{userID}")
    public ResponseEntity<List<BlogDto>> getAllFavBlogs(@PathVariable("userID") Long userID) {
        return ResponseEntity.ok(blogService.getUserFavBlogs(userID));
    }

    @PostMapping(path = "/addToFavs")
    public ResponseEntity<Void> addBlogToFavs(@RequestParam("userID") Long userID,
                                              @RequestParam("blogID") Long blogID) {
        blogService.addBlogToUserFavs(userID, blogID);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/deleteFromFavs")
    public ResponseEntity<Void> deleteBlogFromFavs(@RequestParam("userID") Long userID,
                                                   @RequestParam("blogID") Long blogID) {
        blogService.deleteBlogFromUserFavs(userID, blogID);
        return ResponseEntity.ok().build();
    }

}
