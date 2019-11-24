package com.nc.edu.fapi.service;

import com.nc.edu.fapi.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service("customUserDetailsService")
public class UserService {
    @Value("${backend.server.url}")
    private String backendServerUrl;
    public User findById(long id) {
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(backendServerUrl + "/api/user/id/" + id, User.class);
        return user;
    }
    public User findByLogin(String login) {
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(backendServerUrl + "/api/user/login/" + login, User.class);
        return user;//что это?
    }
    public List<User> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        User[] usersResponse = restTemplate.getForObject(backendServerUrl + "/api/user", User[].class);
        return usersResponse == null ? Collections.emptyList() : Arrays.asList(usersResponse);
    }

    public User save(User user) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/user", user, User.class).getBody();
    }



}
