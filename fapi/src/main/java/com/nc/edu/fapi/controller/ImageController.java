package com.nc.edu.fapi.controller;

import com.nc.edu.fapi.dto.PostPageDto;
import com.nc.edu.fapi.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ImageController {
    @Autowired
    public ImageService imageService;
    @GetMapping(value = "/photo/{name}")
    @ResponseBody
    public ResponseEntity<byte[]> getPhoto(@PathVariable() String name) throws IOException {

        return imageService.getImage(name);
    }
}
