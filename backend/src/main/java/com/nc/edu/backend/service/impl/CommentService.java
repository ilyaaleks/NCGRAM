package com.nc.edu.backend.service.impl;

import com.nc.edu.backend.converter.Converter;
import com.nc.edu.backend.domain.Comment;
import com.nc.edu.backend.domain.Post;
import com.nc.edu.backend.domain.User;
import com.nc.edu.backend.dto.CommentDto;
import com.nc.edu.backend.dto.CommentPageDto;
import com.nc.edu.backend.dto.PostDto;
import com.nc.edu.backend.dto.PostPageDto;
import com.nc.edu.backend.repository.CommentRepository;
import com.nc.edu.backend.repository.UserRepository;
import com.nc.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    public UserService userService;
    @Autowired
    public PostService postService;

    public CommentPageDto getCommentsOfPost(long postId, Pageable pageable) {
        Page<Comment> page = commentRepository.findByPostId(postId, pageable);
        List<Comment> commentList = page.getContent();
        List<CommentDto> commentDtoList = new ArrayList<>();
        Converter converter = new Converter();
        CommentDto commentDto;
        for (Comment comment : commentList) {
            commentDto = converter.converToCommentDto(comment);
            commentDtoList.add(commentDto);
        }
        return new CommentPageDto(commentDtoList,
                pageable.getPageNumber(),
                page.getTotalPages(),
                page.getTotalElements());
    }

    public CommentDto saveComment(CommentDto commentDto) {
        User user = userService.findById(commentDto.getUserId());
        Post post =postService.findById(commentDto.getPostId());
        Converter converter = new Converter();
        Comment comment = converter.convertToComment(commentDto, user, post);
        comment.setDate(new Date());
        comment = commentRepository.save(comment);
        return converter.converToCommentDto(comment);
    }
//
//    public List<Comment> findAll() {
//        return (List<Comment>) commentRepository.findAll();
//    }
//
//    public Optional<User> findById(long id)
//    {
//        return userRepository.findById(id);
//    }
//
//    public User findByLogin(String login) {
//        return userRepository.findByLogin(login);
//    }
//
//    public User save(User user) {
//        return userRepository.save(user);
//    }
//
//    public void delete(long id) {
//        userRepository.deleteById(id);
//    }
//
//    public void saveAll(Iterable<User> users)
//    {
//        userRepository.saveAll(users);
//    }
//    public List<Post> getUserPosts(Long id)
//    {
//        return userRepository.findById(id).get().getPosts();
//
//    }
//    public long count(){
//        return userRepository.count();
//    }

}
