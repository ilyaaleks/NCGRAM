package com.nc.edu.fapi.controller;

import com.nc.edu.fapi.dto.UserDto;
import com.nc.edu.fapi.models.User;
import com.nc.edu.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    public UserService userService;


    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.findById(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return null;
    }


    @PostMapping("/users")
    public Object saveUser(@RequestParam MultipartFile file,
                         @RequestParam String name,
                         @RequestParam String surname,
                         @RequestParam String aboutMe,
                         @RequestParam String login,
                         @RequestParam String password,
                         @RequestParam String email) throws IOException {
        User user = new User(name,surname,email,aboutMe,login,password,null,null,null);
        return userService.save(user,file);

    }


    @GetMapping("/user")
    public User loginUser (@RequestParam String login,
                              @RequestParam String password)
    {
        return userService.userAuth(login,password);
    }

    @GetMapping("/subscribers/count/{userId}")
    public int getCountOfSubscribers(@PathVariable long userId)
    {
        return userService.getCountOfSubscribers(userId);
    }

    @GetMapping("/subscribtions/count/{userId}")
    public int getCountOfSubscribtions(@PathVariable long userId)
    {
        return userService.getCountOfSubscribtions(userId);
    }
    @GetMapping("/user/subscribe")
    public UserDto subscribe(@RequestParam long userId,
                            @RequestParam long currentUserId)
    {
        return userService.subscribe(userId, currentUserId);

    }
    @GetMapping("/user/unsubscribe")
    public UserDto unsubscribe(@RequestParam long userId,
                               @RequestParam long currentUserId)
    {
        return userService.unsubscribe(userId, currentUserId);
    }
}
