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

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByLogin(@PathVariable(name = "id") String id) {
        User user = userService.findById(Long.valueOf(id)).get();//
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }
}
