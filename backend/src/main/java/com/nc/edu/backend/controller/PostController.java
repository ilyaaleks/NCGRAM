package com.nc.edu.backend.controller;

import com.nc.edu.backend.domain.Claim;
import com.nc.edu.backend.domain.Post;
import com.nc.edu.backend.domain.User;
import com.nc.edu.backend.dto.ClaimDto;
import com.nc.edu.backend.dto.PostDto;
import com.nc.edu.backend.dto.PostPageDto;
import com.nc.edu.backend.dto.UserDto;
import com.nc.edu.backend.repository.ClaimRepository;
import com.nc.edu.backend.service.UserService;
import com.nc.edu.backend.service.impl.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@RestController
@RequestMapping("api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private ClaimRepository claimRepository;
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

    @PostMapping("")
    public PostDto savePost(@RequestParam("file")  String fileStr,
                            @RequestParam String fileName,
                            @RequestParam String authorLogin,
                            @RequestParam String hashTags,
                            @RequestParam String text) {
        User user=userService.findByLogin(authorLogin);
        if(user!=null)
        {
        Post post=new Post(user,null,text,new Date(),null,null,null,null);
        return postService.save(post,fileStr,fileName, hashTags);
        }
        throw new IllegalArgumentException();

    }
    @GetMapping("/count/{userId}")
    public UserDto countOfPosts(@PathVariable long userId)
    {
        User user =userService.findById(userId);
        UserDto userDto=new UserDto();
        userDto.setCountOfPosts(user.getPosts().size());
        return userDto;
    }

    @GetMapping("/userPosts/{userId}")
    public PostPageDto getUserPosts(@PathVariable long userId, Pageable pageable)
    {

        PostPageDto page= postService.getPostOfAuthor(userId,pageable);
        return page;
    }
    @GetMapping("/postsByTag/{tagId}")
    public PostPageDto getPostsByTag(@PathVariable long tagId, Pageable pageable)
    {

        PostPageDto page= postService.getPostsByTag(tagId,pageable);
        return page;
    }
    @GetMapping("/subscribtionPosts/{userId}")
    public PostPageDto getSubscribtionPosts(@PathVariable long userId, Pageable pageable)
    {

        PostPageDto page= postService.getSubscribtionPosts(userId,pageable);
        return page;
    }
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable long id)
    {
        postService.delete(id);
    }
    @PutMapping("")
    public PostDto updatePost(@RequestParam("file")  String fileStr,
                              @RequestParam String fileName,
                              @RequestParam String authorLogin,
                              @RequestParam String hashTags,
                              @RequestParam String text,
                              @RequestParam long postId)
    {
        User user=userService.findByLogin(authorLogin);
        if(user!=null)
        {
            Post post=new Post(user,null,text,new Date(),null,null,null,null);
            post.setId(postId);
            return postService.update(post,fileStr,fileName, hashTags);
        }
        throw new IllegalArgumentException();
    }
    @PostMapping("/claim")
    public ClaimDto saveClaim(@RequestBody ClaimDto claimDto)
    {
        User user=userService.findById(claimDto.getAuthorId());
        Post post=postService.findById(claimDto.getPostId());
        Claim claim=new Claim(post,new Date(),claimDto.getReason(),claimDto.getStatus(),user);
        claimRepository.save(claim);
        claimDto=new ClaimDto(claim.getId(),claim.getPost().getId(),claim.getDate(),claim.getReason(),claim.getStatus(),claim.getAuthor().getId());
        return claimDto;
    }
}
