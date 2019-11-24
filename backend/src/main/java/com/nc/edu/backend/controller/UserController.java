package com.nc.edu.backend.controller;

import com.nc.edu.backend.domain.User;
import com.nc.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<User> getUserByLogin(@PathVariable(name = "id") long id) {
        User user = userService.findById(id).get();//
        return ResponseEntity.ok(user);
    }
    @RequestMapping(value = "/login/{login}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByLogin(@PathVariable(name = "login") String login) {//vs requestparam???
        User user = userService.findByLogin(login);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }
}
