package com.nc.edu.fapi.service;

import com.nc.edu.fapi.dto.ClaimDto;
import com.nc.edu.fapi.dto.PostDto;
import com.nc.edu.fapi.dto.PostPageDto;
import com.nc.edu.fapi.dto.UserDto;
import com.nc.edu.fapi.models.Post;
import com.nc.edu.fapi.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

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


    public PostDto savePost(Post post, MultipartFile file, String hashTags) throws IOException {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        RestTemplate restTemplate = new RestTemplate();
        String encoded = Base64.getEncoder().encodeToString(file.getBytes());
        parameters.add("file", encoded);//передача бинарных файлов, filetra
        parameters.add("fileName",file.getOriginalFilename());
        parameters.add("authorLogin", post.getAuthorLogin());
        parameters.add("hashTags", hashTags);
        parameters.add("text", post.getText());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, String>> requestEntity
                = new HttpEntity<>(parameters, headers);
        return restTemplate.postForEntity(backendServerUrl + "/api/posts", requestEntity, PostDto.class).getBody();//id post

    }
    public PostDto updatePost(Post post, MultipartFile file, String hashTags) throws IOException {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        RestTemplate restTemplate = new RestTemplate();
        String encoded = Base64.getEncoder().encodeToString(file.getBytes());
        parameters.add("file", encoded);//передача бинарных файлов, filetra
        parameters.add("fileName",file.getOriginalFilename());
        parameters.add("authorLogin", post.getAuthorLogin());
        parameters.add("hashTags", hashTags);
        parameters.add("text", post.getText());
        parameters.add("postId",Long.toString(post.getId()));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, String>> requestEntity
                = new HttpEntity<>(parameters, headers);
        return restTemplate.exchange(backendServerUrl + "/api/posts", HttpMethod.PUT, requestEntity, PostDto.class).getBody();//id post

    }
    public int countOfPosts(long id)
    {
        RestTemplate restTemplate=new RestTemplate();
        UserDto userDto=restTemplate.getForObject(backendServerUrl+"/api/posts/count/"+id,UserDto.class);
        return userDto.getCountOfPosts();
    }

    public void deletePost(long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/posts/" + id);
    }

    public PostPageDto getUserPosts(long userId, Pageable pageable) {
        RestTemplate restTemplate = new RestTemplate();
        PostPageDto posts= restTemplate.getForObject(backendServerUrl + "/api/posts/userPosts/"+userId+"?page="+pageable.getPageNumber()+"&size=5&sort=id,DESC", PostPageDto.class);
        return posts;
    }

    public PostPageDto getPostsByTag(long tagId, Pageable pageable) {
        RestTemplate restTemplate = new RestTemplate();
        PostPageDto posts= restTemplate.getForObject(backendServerUrl + "/api/posts/postsByTag/"+tagId+"?page="+pageable.getPageNumber()+"&size=5&sort=id,DESC", PostPageDto.class);
        return posts;
    }

    public PostPageDto getSubscribtionPosts(long userId, Pageable page) {
        RestTemplate restTemplate = new RestTemplate();
        PostPageDto posts= restTemplate.getForObject(backendServerUrl + "/api/posts/subscribtionPosts/"+userId+"?page="+page.getPageNumber()+"&size=5&sort=id,DESC", PostPageDto.class);
        return posts;
    }


    public ClaimDto saveClaim(ClaimDto claimDto) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/posts/claim", claimDto, ClaimDto.class).getBody();
    }
}
