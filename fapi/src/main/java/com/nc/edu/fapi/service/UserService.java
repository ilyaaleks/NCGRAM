package com.nc.edu.fapi.service;

import com.nc.edu.fapi.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.List;

@Service("customUserDetailsService")
public class UserService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    public User findById(long id) {
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(backendServerUrl + "/api/user/" + id, User.class);
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

    public Object save(User user, MultipartFile file) throws IOException {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        RestTemplate restTemplate = new RestTemplate();
        String encoded = Base64.getEncoder().encodeToString(file.getBytes());
        parameters.add("file", encoded);//передача бинарных файлов, filetra
        parameters.add("fileName",file.getOriginalFilename());
        parameters.add("name", user.getName());
        parameters.add("surname", user.getSurname());
        parameters.add("aboutMe", user.getAboutMe());
        parameters.add("login", user.getLogin());
        parameters.add("password", user.getPassword());
        parameters.add("email", user.getEmail());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, String>> requestEntity
                = new HttpEntity<>(parameters, headers);
        return restTemplate.postForEntity(backendServerUrl + "/api/user", requestEntity, String.class);
    }


}
