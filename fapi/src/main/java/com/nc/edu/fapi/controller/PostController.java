package com.nc.edu.fapi.controller;


import com.nc.edu.fapi.dto.ClaimDto;
import com.nc.edu.fapi.dto.PostDto;
import com.nc.edu.fapi.dto.PostPageDto;
import com.nc.edu.fapi.dto.UserDto;
import com.nc.edu.fapi.models.HashTag;
import com.nc.edu.fapi.models.Post;
import com.nc.edu.fapi.models.User;
import com.nc.edu.fapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    public PostService postService;


    @GetMapping("/posts")
    public PostPageDto getPosts(Pageable page) {
        PostPageDto postPageDto = postService.getAll(page);
        return postPageDto;

    }

    @PostMapping("/posts")
    public PostDto savePost(@RequestParam(name = "file") MultipartFile file,//можно ли возвращать object?
                            @RequestParam String authorLogin,
                            @RequestParam String hashTags,
                            @RequestParam String text) throws IOException {
        PostDto savePost = postService.savePost(new Post(authorLogin, null, text, new Date(), null), file, hashTags);
        return savePost;
    }

    @GetMapping("/posts/{userId}")
    public String numberOfPosts(
            @PathVariable() long userId,
            @RequestParam(name = "count") boolean flag) {

        return Integer.toString(postService.countOfPosts(userId));

    }

    @GetMapping("/userPosts/{userId}")
    public PostPageDto getUserPosts(@PathVariable() long userId,
                                    Pageable page) {
        return postService.getUserPosts(userId, page);
    }

    @GetMapping("/postsByTag/{tagId}")
    public PostPageDto getPostsByTag(@PathVariable() long tagId,
                                     Pageable page)//,@RequestParam long tagId
    {
        return postService.getPostsByTag(tagId, page);
    }

    @GetMapping("/subscribtionPosts/{userId}")
    public PostPageDto getSubscribtionPosts(@PathVariable() long userId,
                                    Pageable page) {
        return postService.getSubscribtionPosts(userId, page);
    }
    @DeleteMapping("/post/{postId}")
    public void getSubscribtionPosts(@PathVariable() long postId) {
       postService.deletePost(postId);
    }
    @PutMapping("/posts")
    public PostDto updatePost(@RequestParam(name = "file") MultipartFile file,//можно ли возвращать object?
                            @RequestParam String authorLogin,
                            @RequestParam String hashTags,
                            @RequestParam String text,
                              @RequestParam long postId) throws IOException {
        PostDto savePost = postService.updatePost(new Post(postId,authorLogin, null, text, new Date(), null), file, hashTags);
        return savePost;
    }
    @PostMapping("/posts/claim")
    public ClaimDto saveClaim(@RequestBody ClaimDto claimDto) throws IOException {
        ClaimDto savePost = postService.saveClaim(claimDto);
        return savePost;
    }
}
