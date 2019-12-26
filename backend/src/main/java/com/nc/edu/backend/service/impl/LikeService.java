package com.nc.edu.backend.service.impl;

import com.nc.edu.backend.converter.Converter;
import com.nc.edu.backend.domain.LikeOrDislike;
import com.nc.edu.backend.domain.Post;
import com.nc.edu.backend.domain.TypeOfVote;
import com.nc.edu.backend.domain.User;
import com.nc.edu.backend.dto.LikeOrDislikeDto;
import com.nc.edu.backend.repository.LikeRepository;
import com.nc.edu.backend.repository.PostRepository;
import com.nc.edu.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    public LikeOrDislikeDto getCountOfLike(long postId, long userId) {
        Post post=postRepository.findById(postId);
        User user=userRepository.findById(userId);
        Set<LikeOrDislike> likes =post.getLikeOrDislikes();
        int countOfLikes=0;
        int countOfDislikes=0;
        for(LikeOrDislike item:likes)
        {
            if(item.getTypeOfVote().equals(TypeOfVote.Like))
            {
                countOfLikes++;
            }
            else if(item.getTypeOfVote().equals(TypeOfVote.Dislike))
            {
                countOfDislikes++;
            }
        }
        LikeOrDislike likeOrDislike=likeRepository.findByPostId(postId,userId);
        if(likeOrDislike==null)
        {
            likeOrDislike=new LikeOrDislike(post,user,TypeOfVote.Nothing,new Date());
            likeRepository.save(likeOrDislike);
        }
        LikeOrDislikeDto likeOrDislikeDto=new LikeOrDislikeDto(likeOrDislike.getId(),postId,userId,likeOrDislike.getTypeOfVote(),likeOrDislike.getDate());
        likeOrDislikeDto.setCountOfLikes(countOfLikes);
        likeOrDislikeDto.setCountOfDislikes(countOfDislikes);
        return likeOrDislikeDto;

    }

    public LikeOrDislikeDto saveLike(LikeOrDislikeDto likeOrDislikeDto) {
        Converter converter=new Converter();
        User user=userRepository.findById(likeOrDislikeDto.getAuthorId());
        Post post=postRepository.findById(likeOrDislikeDto.getPostId());
        int countOfLikes=0;
        int countOfDislikes=0;

        LikeOrDislike likeOrDislike=converter.convertToLike(likeOrDislikeDto,user,post);
        if(likeOrDislikeDto.getId()!=0)
        {
            likeOrDislike.setId(likeOrDislikeDto.getId());
        }
        likeRepository.save(likeOrDislike);
        post=postRepository.findById(likeOrDislikeDto.getPostId());
        Set<LikeOrDislike> likes =post.getLikeOrDislikes();
        for(LikeOrDislike item:likes)
        {
            if(item.getTypeOfVote().equals(TypeOfVote.Like))
            {
                countOfLikes++;
            }
            else if(item.getTypeOfVote().equals(TypeOfVote.Dislike))
            {
                countOfDislikes++;
            }
        }
        likeOrDislikeDto=converter.convertToLikeDto(likeOrDislike);
        likeOrDislikeDto.setCountOfDislikes(countOfDislikes);
        likeOrDislikeDto.setCountOfLikes(countOfLikes);
        return likeOrDislikeDto;

    }
}
