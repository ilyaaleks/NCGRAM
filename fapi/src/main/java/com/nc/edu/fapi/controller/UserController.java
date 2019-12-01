package com.nc.edu.fapi.controller;

import com.nc.edu.fapi.models.User;
import com.nc.edu.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User getUserByLogin(@PathVariable long id) {
        return null;
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

}
