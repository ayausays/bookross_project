package com.bookross.mainservice.demo.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveUserImage(Long id, MultipartFile multipartFile);
    void saveBookImage(Long id, MultipartFile multipartFile);
}
