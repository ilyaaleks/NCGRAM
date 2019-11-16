package com.nc.edu.fapi.controller;

import com.nc.edu.fapi.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
    ArrayList<User> userList=new ArrayList<User>();
    User user1=new User(1,"ilya","alekseev","Love animals","ilya","ilya","user","acrive","http://localhost:4200/assets/img/1508101437141939217.jpg");
    User user2=new User(2,"aleksey","chernyavskiy","Love cars","lexa","lexa","user","active","http://localhost:4200/assets/img/Image00008-4.jpg");
    @GetMapping("/user/{login}")
    public User getUserByLogin(@PathVariable String login) {
        userList.add(user1);
        userList.add(user2);
        return user1;
    }
    @GetMapping("/users")
    public List<User> getAllUsers() {
        userList.add(user1);
        userList.add(user2);
        return userList;
    }

}
