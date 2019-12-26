package com.nc.edu.fapi.converter;

import com.nc.edu.fapi.dto.HashTagDto;
import com.nc.edu.fapi.dto.PostDto;
import com.nc.edu.fapi.dto.UserDto;
import com.nc.edu.fapi.models.HashTag;
import com.nc.edu.fapi.models.Post;
import com.nc.edu.fapi.models.User;
import com.nc.edu.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class Converter {
    @Autowired
    public UserService userService;

    public UserDto convertToUserDto(User user)
    {
        return new UserDto(user.getId(),user.getName(),user.getSurname(), user.getEmail(),user.getAboutMe(),
                user.getLogin(),user.getPassword(),user.getRole(),user.getStatus(),user.getPhotoUrl());
    }
    public User converToUser(UserDto userDto)
    {
        return new User(userDto.getName(), userDto.getSurname(), userDto.getEmail(), userDto.getAboutMe(),userDto.getLogin(), userDto.getPassword(), userDto.getRole(),
                userDto.getStatus(), userDto.getPhotoUrl());
    }
    public PostDto converToPostDto(Post post)
    {
        return new PostDto(post.getId(),post.getAuthorLogin(),post.getPhotoPath(),post.getText(),post.getDate(),post.getHashTags());
    }
    public Post convertToPost(PostDto postDto)
    {
        User user=userService.findById(postDto.getAuthorId(),0);
        return new Post(user.getLogin(),postDto.getPhotoPath(),postDto.getText(),postDto.getDate(),postDto.getHashTags());
    }
    public Set<HashTagDto> convertToHashTagDto(Set<HashTag> hashTags)
    {
        Set<HashTagDto> hashTagDtoSet=new HashSet<>();
        for(HashTag hashTag:hashTags)
        {
            hashTagDtoSet.add(new HashTagDto(hashTag.getId(),hashTag.getText()));
        }
        return hashTagDtoSet;
    }


}
