package com.nc.edu.backend.controller;

import com.nc.edu.backend.dto.CommentDto;
import com.nc.edu.backend.dto.CommentPageDto;
import com.nc.edu.backend.service.impl.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping("/comment/{postId}")
    public ResponseEntity<CommentPageDto> getCommentsOfPost(@PathVariable() long postId,
                                                            Pageable page) {
        return ResponseEntity.ok(commentService.getCommentsOfPost(postId,page));
    }

    @PostMapping("/comment")
    public ResponseEntity<CommentDto> saveComment(@RequestBody CommentDto commentDto) {
        if (commentDto != null) {
            return ResponseEntity.ok(commentService.saveComment(commentDto));
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }
}
