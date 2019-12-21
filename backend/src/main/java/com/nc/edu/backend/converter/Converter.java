package com.nc.edu.backend.converter;

import com.nc.edu.backend.domain.HashTag;
import com.nc.edu.backend.domain.Post;
import com.nc.edu.backend.domain.User;
import com.nc.edu.backend.dto.HashTagDto;
import com.nc.edu.backend.dto.PostDto;
import com.nc.edu.backend.dto.UserDto;
import com.nc.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class Converter {
    @Autowired
    public UserService userService;

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

    public Post convertToPost(PostDto postDto) {
        User user = userService.findById(postDto.getAuthorId());
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
        return null;
    }
}
