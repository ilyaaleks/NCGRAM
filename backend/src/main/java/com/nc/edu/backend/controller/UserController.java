package com.nc.edu.backend.controller;

import com.nc.edu.backend.domain.LikeOrDislike;
import com.nc.edu.backend.domain.User;
import com.nc.edu.backend.service.UserService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
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
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserByLogin(@PathVariable(name = "id") long id) {
        User user = userService.findById(id);
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
    public User saveUser(@RequestParam("file")  String fileStr,
                         @RequestParam String name,
                         @RequestParam String fileName,
                         @RequestParam String surname,
                         @RequestParam String aboutMe,
                         @RequestParam String login,
                         @RequestParam String password,
                         @RequestParam String email) throws IOException {
        User user=new User(name,surname,email,aboutMe,login,password,null,null,null,null,null,null,null,null);
        String decoded = new String(Base64.getDecoder().decode(fileStr));
        byte[] file = Base64.getDecoder().decode(fileStr);
        if (file != null) {
            File uploadDir = new File(uploadPath);//и перенести все в сервис?
            if (!uploadDir.exists()) {//еще раз спросить про реализацию раздачи картинки
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + fileName;//теперь не могу отобразить картинку
            try (FileOutputStream fos = new FileOutputStream(uploadPath+"/"+resultFilename)) {
                fos.write(file);
                fos.close();
            }


            user.setPhotoUrl(resultFilename);
        }
        return userService.save(user);
    }
}
