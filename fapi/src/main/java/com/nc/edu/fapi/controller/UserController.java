package com.nc.edu.fapi.controller;

import com.nc.edu.fapi.models.User;
import com.nc.edu.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    public UserService userService;
    @Value("${upload.path}")
    private String uploadPath;
    //    @GetMapping("/user/{id}")
//    public User getUserByLogin(@PathVariable long id) {
//
//    }
//    @GetMapping("/users")
//    public List<User> getAllUsers() {
//
//    }
    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {
        return userService.save(user);

    }
    @PostMapping("/user/photo/{login}")
    public User saveUserPhoto(@PathVariable String login, @RequestParam MultipartFile file) throws IOException {

        User user =userService.findByLogin(login);
        if(file!=null)
        {
            File uploadDir=new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile =UUID.randomUUID().toString();
            String resultFilename=uuidFile+"."+file.getOriginalFilename();
            file.transferTo(new File(uploadPath+"/"+resultFilename));
            user.setPhotoUrl(resultFilename);
        }
        return userService.save(user);
    }


}
