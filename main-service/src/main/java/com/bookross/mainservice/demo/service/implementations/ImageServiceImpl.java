package com.bookross.mainservice.demo.service.implementations;

import com.bookross.mainservice.demo.entity.AppUserDetails;
import com.bookross.mainservice.demo.entity.Book;
import com.bookross.mainservice.demo.service.interfaces.AppUserDetailsService;
import com.bookross.mainservice.demo.service.interfaces.BookService;
import com.bookross.mainservice.demo.service.interfaces.ImageService;
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

    private final AppUserDetailsService appUserDetailsService;
    private final BookService bookService;
    private final String USER_IMAGES_DIR = "C:\\Users\\Yser\\Desktop\\Projects\\bookross\\main-service\\src\\userImages";
    private final String BOOK_IMAGES_DIR = "C:\\Users\\Yser\\Desktop\\Projects\\bookross\\main-service\\src\\bookImages";


    @Override
    public void saveUserImage(Long id, MultipartFile multipartFile) {
            String filePath = USER_IMAGES_DIR + File.separator + UUID.randomUUID() + ".jpg";
            putImageToFolder(filePath, multipartFile);
            AppUserDetails details = appUserDetailsService.findOrThrowNotFound(id);
            if (details.getImagePath() != null) {
                File oldFile = new File(details.getImagePath());
                oldFile.delete();
            }
            details.setImagePath(filePath);
            appUserDetailsService.save(details);
        }

    @Override
    public void saveBookImage(Long id, MultipartFile multipartFile) {
        String filePath = BOOK_IMAGES_DIR + File.separator + UUID.randomUUID() + ".jpg";
        putImageToFolder(filePath, multipartFile);
        Book book = bookService.findOrThrowNotFound(id);
        if (book.getImagePath() != null){
            File oldFile = new File(book.getImagePath());
            oldFile.delete();
        }
        book.setImagePath(filePath);
        bookService.save(book);

    }


    private void putImageToFolder(String filePath, MultipartFile multipartFile){
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
        }
        catch (Exception ex) {
            System.out.println("error" + ex);
        }
    }

}
