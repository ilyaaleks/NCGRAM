package com.nc.edu.backend.controller;

import com.nc.edu.backend.service.impl.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/photo/{name}")
    @ResponseBody
    public ResponseEntity<byte[]> getPhoto(@PathVariable() String name) throws IOException {
       return imageService.getPhoto(name);
    }
}
