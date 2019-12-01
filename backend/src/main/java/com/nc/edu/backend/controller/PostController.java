package com.nc.edu.backend.controller;

import com.nc.edu.backend.domain.Post;
import com.nc.edu.backend.dto.PostPageDto;
import com.nc.edu.backend.service.impl.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable(name = "id") long id) {
        Post post = postService.findById(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping(value = "")
    public PostPageDto getAllPosts(Pageable pageable) {
        PostPageDto postPageDto=postService.findAll(pageable);
        return postPageDto;
    }

    @PostMapping
    public Post savePost(@RequestBody Post post) {

        return postService.save(post);

    }
}
