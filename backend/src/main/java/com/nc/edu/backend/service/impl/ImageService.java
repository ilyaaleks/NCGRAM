package com.nc.edu.backend.service.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class ImageService {
    public ResponseEntity<byte[]> getPhoto(String name) throws IOException {

        File imgPath = new File("D:\\5sem\\NC\\fakegram\\backend\\src\\main\\resources\\images\\"+name);

        byte[] image = Files.readAllBytes(imgPath.toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(image.length);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }
}
