package com.nc.edu.backend.converter;

import com.nc.edu.backend.domain.*;
import com.nc.edu.backend.dto.*;
import com.nc.edu.backend.service.UserService;
import com.nc.edu.backend.service.impl.PostService;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Converter {

    public UserDto convertToUserDto(User user) throws UserPrincipalNotFoundException {
        if(user!=null) {
            return new UserDto(user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getAboutMe(),
                    user.getLogin(), user.getPassword(), user.getRole(), user.getStatus(), user.getPhotoUrl());
        }
        else
        {
            throw new UserPrincipalNotFoundException("User not found");
        }
    }

    public User converToUser(UserDto userDto) throws UserPrincipalNotFoundException {
        if(userDto!=null) {
            return new User(userDto.getName(), userDto.getSurname(), userDto.getEmail(), userDto.getAboutMe(), userDto.getLogin(), userDto.getPassword(), userDto.getRole(),
                    userDto.getStatus(), userDto.getPhotoUrl());
        }
        else
        {
            throw new UserPrincipalNotFoundException("User not found");
        }
    }

    public PostDto converToPostDto(Post post) {
        PostDto postDto=new PostDto(post.getId(), post.getAuthor().getId(),post.getAuthor().getLogin(), post.getPhotoPath(), post.getText(), post.getDate(), this.convertToHashTagDto(post.getHashTags()));
        postDto.setAuthorPhotoPath(post.getAuthor().getPhotoUrl());
        return postDto;
    }

    public Post convertToPost(PostDto postDto, User user) {
        return new Post(user, postDto.getPhotoPath(), postDto.getText(), postDto.getDate());
    }

    public Set<HashTagDto> convertToHashTagDto(Set<HashTag> hashTags) {
        if (hashTags != null) {
            Set<HashTagDto> hashTagDtoSet = new HashSet<>();
            for (HashTag hashTag : hashTags) {
                hashTagDtoSet.add(new HashTagDto(hashTag.getId(), hashTag.getText()));
            }
            return hashTagDtoSet;
        }
        throw new IllegalArgumentException();
    }
    public CommentDto converToCommentDto(Comment comment)
    {
        if(comment!=null) {
            return new CommentDto(comment.getId(), comment.getText(),comment.getDate(), comment.getPostComment().getId(),
                    comment.getAuthor().getId(), comment.getAuthor().getLogin(), comment.getAuthor().getPhotoUrl());
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
    public Comment convertToComment(CommentDto commentDto, User user, Post post)
    {
        if(commentDto!=null)
        {
            return new Comment(user,commentDto.getText(),commentDto.getDate(),post);
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
    public LikeOrDislike convertToLike(LikeOrDislikeDto likeOrDislikeDto,User author, Post post)
    {
        if(likeOrDislikeDto!=null)
        {
            return new LikeOrDislike(post,author,likeOrDislikeDto.getTypeOfVote(),new Date());
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
    public LikeOrDislikeDto convertToLikeDto(LikeOrDislike likeOrDislike)
    {
        if(likeOrDislike!=null)
        {
            return new LikeOrDislikeDto(likeOrDislike.getId(),likeOrDislike.getPost().getId(),likeOrDislike.getAuthor().getId(),likeOrDislike.getTypeOfVote(),likeOrDislike.getDate());
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
}
