package com.nc.edu.fapi.controller;


import com.nc.edu.fapi.dto.PostPageDto;
import com.nc.edu.fapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    public PostService postService;


    @GetMapping("/posts")
    public PostPageDto getPosts(Pageable page) {
        PostPageDto postPageDto=postService.getAll(page);
        return postPageDto;

    }
}
