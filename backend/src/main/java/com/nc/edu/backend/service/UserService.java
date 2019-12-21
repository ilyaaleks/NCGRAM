package com.nc.edu.backend.service;

import com.nc.edu.backend.domain.User;
import com.nc.edu.backend.dto.UserDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


public interface UserService {

    List<User> findAll();
    User save(User user, String fileStr, String fileName);
    void delete(long id);
    User findByLogin(String login);
    User findById(long id);


    UserDto getCountOfSubscribers(long userId);

    UserDto getCountOfSubscribtions(long userId);

    UserDto unsubscribe(int userId, int currentUserId);

    UserDto subscribe(int userId, int currentUserId);

    List<UserDto> getSubscribers(long userId);

    List<UserDto> getSubscriptions(long userId);
}
