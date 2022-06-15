package com.bookross.mainservice.demo.service.implementations;

import com.bookross.mainservice.demo.entity.AppUser;
import com.bookross.mainservice.demo.entity.AppUserDetails;
import com.bookross.mainservice.demo.entity.Blog;
import com.bookross.mainservice.demo.entity.Book;
import com.bookross.mainservice.demo.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import java.io.*;
import java.util.UUID;

@Service
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024 * 100)
@RequiredArgsConstructor
public class ImageServiceImpl extends HttpServlet implements ImageService {

    private final AppUserService appUserService;
    private final AppUserDetailsService appUserDetailsService;
    private final BookService bookService;
    private final BlogService blogService;
    private final String USER_IMAGES_DIR = "C:\\Users\\Yser\\Desktop\\Projects\\bookross\\main-service\\src\\images\\userImages";
    private final String BOOK_IMAGES_DIR = "C:\\Users\\Yser\\Desktop\\Projects\\bookross\\main-service\\src\\images\\bookImages";
    private final String BLOG_IMAGES_DIR = "C:\\Users\\Yser\\Desktop\\Projects\\bookross\\main-service\\src\\images\\blogImages";


    @Override
    public void saveUserImage(Long id, MultipartFile multipartFile) {
        String uid = UUID.randomUUID() + ".jpg";
        String filePath = USER_IMAGES_DIR + uid;
        putImageToFolder(filePath, multipartFile);
        AppUser appUser = appUserService.getUserById(id);
        AppUserDetails appUserDetails = appUser.getAppUserDetails() == null ? new AppUserDetails() : appUser.getAppUserDetails();
        if (appUserDetails.getImagePath() != null) {
            File oldFile = new File(appUserDetails.getImagePath());
            oldFile.delete();
        }
        appUserDetails.setImagePath(filePath);
        appUserDetails = appUserDetailsService.save(appUserDetails);
        appUser.setAppUserDetails(appUserDetails);
        appUserService.save(appUser);
    }

    @Override
    public void saveBookImage(Long id, MultipartFile multipartFile) {
        String uid = UUID.randomUUID() + ".jpg";
        String filePath = BOOK_IMAGES_DIR + File.separator + uid;
        putImageToFolder(filePath, multipartFile);
        Book book = bookService.findOrThrowNotFound(id);
        if (book.getImagePath() != null) {
            File oldFile = new File(book.getImagePath());
            oldFile.delete();
        }
        book.setImagePath(filePath);
        bookService.save(book);

    }

    @Override
    public void saveBlogImage(Long id, MultipartFile multipartFile) {
        String uid = UUID.randomUUID() + ".jpg";
        String filePath = BLOG_IMAGES_DIR + File.separator + uid;
        putImageToFolder(filePath, multipartFile);
        Blog blog = blogService.findOrThrowNotFound(id);
        if (blog.getImagePath() != null){
            File oldFile = new File(blog.getImagePath());
            oldFile.delete();
        }
        blog.setImagePath(filePath);
        blogService.save(blog);
    }


    private void putImageToFolder(String filePath, MultipartFile multipartFile) {
        try {
            File file = new File(filePath);
            try (
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)
            ) {
                bufferedOutputStream.write(multipartFile.getBytes());
                bufferedOutputStream.flush();
            } catch (FileNotFoundException e) {
                throw new RuntimeException("File not found error", e);
            } catch (IOException e) {
                throw new RuntimeException("IO error", e);
            }
        } catch (Exception ex) {
            System.out.println("error" + ex);
        }
    }

}
