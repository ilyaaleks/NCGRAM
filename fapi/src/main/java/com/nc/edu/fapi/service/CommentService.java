package com.nc.edu.fapi.service;

import com.nc.edu.fapi.dto.CommentDto;
import com.nc.edu.fapi.dto.CommentPageDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;

@Service
public class CommentService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    public CommentPageDto getCommentsOfPost(long postId, Pageable page) {
        RestTemplate restTemplate = new RestTemplate();
        CommentPageDto commentPageDto = restTemplate.getForObject(backendServerUrl + "/api/comment/"+postId+"?page="+page.getPageNumber()+"&size=5&sort=id,DESC", CommentPageDto.class);
        return commentPageDto;
    }

    public CommentDto saveComment(CommentDto commentDto) {
        RestTemplate restTemplate = new RestTemplate();
        CommentDto commentDto1=restTemplate.postForEntity(backendServerUrl + "/api/comment", commentDto, CommentDto.class).getBody();
        return commentDto1;
    }
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public void deleteBillingAccount(@PathVariable String id) {
//        billingAccountDataService.deleteBillingAccount(Long.valueOf(id));
//    }
}
