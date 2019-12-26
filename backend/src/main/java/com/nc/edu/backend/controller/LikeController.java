package com.nc.edu.backend.controller;

import com.nc.edu.backend.dto.LikeOrDislikeDto;
import com.nc.edu.backend.service.impl.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class LikeController {
    @Autowired
    public LikeService likeService;

    @GetMapping("/like/count/{postId}")
    public ResponseEntity<LikeOrDislikeDto> getCountOfLike(@PathVariable() long postId,
                                                           @RequestParam() long userId) {
        return ResponseEntity.ok(likeService.getCountOfLike(postId, userId));
    }

    @PostMapping("/like")
    public ResponseEntity<LikeOrDislikeDto> saveLike(@RequestBody LikeOrDislikeDto likeOrDislikeDto) {
        if (likeOrDislikeDto != null) {
            return ResponseEntity.ok(likeService.saveLike(likeOrDislikeDto));
        } else {
            throw new IllegalArgumentException();
        }
    }

}
