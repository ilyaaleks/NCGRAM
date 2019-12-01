package com.nc.edu.backend.service;

import com.nc.edu.backend.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


public interface UserService {

    List<User> findAll();
    User save(User user);
    void delete(long id);
    User findByLogin(String login);
    User findById(long id);
}
