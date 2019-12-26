package com.nc.edu.fapi.service;

import com.nc.edu.fapi.dto.CommentDto;
import com.nc.edu.fapi.dto.CommentPageDto;
import com.nc.edu.fapi.dto.LikeOrDislikeDto;
import com.nc.edu.fapi.models.LikeOrDislike;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LikeService {
    @Value("${backend.server.url}")
    private String backendServerUrl;


    public LikeOrDislikeDto saveLike(LikeOrDislikeDto likeDto) {
        RestTemplate restTemplate = new RestTemplate();
        LikeOrDislikeDto likeOrDislikeDto=restTemplate.postForEntity(backendServerUrl + "/api/like", likeDto, LikeOrDislikeDto.class).getBody();
        return likeOrDislikeDto;
    }

    public LikeOrDislikeDto getCountOfLike(long postId, long userId) {
        RestTemplate restTemplate = new RestTemplate();
        LikeOrDislikeDto likeOrDislikeDto = restTemplate.getForObject(backendServerUrl + "/api/like/count/"+postId+"?userId="+userId, LikeOrDislikeDto.class);
        return likeOrDislikeDto;
    }
}
