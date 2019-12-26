package com.nc.edu.fapi.controller;

import com.nc.edu.fapi.dto.CommentDto;
import com.nc.edu.fapi.dto.CommentPageDto;
import com.nc.edu.fapi.dto.LikeOrDislikeDto;
import com.nc.edu.fapi.models.LikeOrDislike;
import com.nc.edu.fapi.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class LikeController {
    @Autowired
    public LikeService likeService;

    @GetMapping("/like/count/{postId}")
    public ResponseEntity<LikeOrDislikeDto> getCountOfLike(@PathVariable() long postId,
                                                           @RequestParam() long userId) {
        return ResponseEntity.ok(likeService.getCountOfLike(postId,userId));
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
