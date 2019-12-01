package com.nc.edu.fapi.service;

import com.nc.edu.fapi.dto.PostPageDto;
import com.nc.edu.fapi.models.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostService {
    @Value("${backend.server.url}")
    private String backendServerUrl;


    public PostPageDto getAll(
            Pageable pageable
    ) {
        RestTemplate restTemplate = new RestTemplate();
        PostPageDto posts= restTemplate.getForObject(backendServerUrl + "/api/posts?page="+pageable.getPageNumber()+"&size=5&sort=id,DESC", PostPageDto.class);
        return posts;
    }


    public Post getPostById(Long id) {
        return null;
    }


    public Post savePost(Post post) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/posts", post, Post.class).getBody();
    }


    public void deletePost(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/posts/" + id);
    }
}
