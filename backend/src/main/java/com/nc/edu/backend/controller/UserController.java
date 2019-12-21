package com.nc.edu.backend.controller;

import com.nc.edu.backend.converter.Converter;
import com.nc.edu.backend.domain.LikeOrDislike;
import com.nc.edu.backend.domain.Post;
import com.nc.edu.backend.domain.User;
import com.nc.edu.backend.dto.UserDto;
import com.nc.edu.backend.service.UserService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable() long id) {
        Converter converter = new Converter();
        User user=userService.findById(id);
        user.setPhotoUrl(user.getPhotoUrl());
        return ResponseEntity.ok(converter.convertToUserDto(user));
    }

    @GetMapping("/login/{login}")
    public ResponseEntity<UserDto> getUserByLogin(@PathVariable(name = "login") String login) {//vs requestparam???
        User user = userService.findByLogin(login);
        Converter converter = new Converter();
        UserDto userDto = converter.convertToUserDto(user);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping(value = "")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping
    public User saveUser(@RequestParam("file") String fileStr,
                         @RequestParam String fileName,
                         @RequestParam String name,
                         @RequestParam String surname,
                         @RequestParam String aboutMe,
                         @RequestParam String login,
                         @RequestParam String password,
                         @RequestParam String email) throws IOException {
        User user = new User(name, surname, email, aboutMe, login, password, null, null, null, null, null, null, null, null);

        return userService.save(user, fileStr, fileName);
    }

    @GetMapping("/subscribers/count/{userId}")
    public UserDto getCountOfSubscribers(@PathVariable() long userId) {
        return userService.getCountOfSubscribers(userId);
    }

    @GetMapping("/subscriptions/count/{userId}")
    public UserDto getCountOfSubscribtions(@PathVariable() long userId) {
        return userService.getCountOfSubscribtions(userId);
    }
    @GetMapping("/subscribe")
    public UserDto subscribe(@RequestParam int userId,
                            @RequestParam int currentUserId)
    {
        return userService.subscribe(userId, currentUserId);

    }
    @GetMapping("/unsubscribe")
    public UserDto unsubscribe(@RequestParam int userId,
                              @RequestParam int currentUserId)
    {
        return userService.unsubscribe(userId, currentUserId);
    }

    @GetMapping("/subscribers/{userId}")
    public List<UserDto> getSubscribers(@PathVariable() long userId) {
        return userService.getSubscribers(userId);
    }

    @GetMapping("/subscriptions/{userId}")
    public List<UserDto> getSubscriptions(@PathVariable() long userId) {
        return userService.getSubscriptions(userId);
    }


}
