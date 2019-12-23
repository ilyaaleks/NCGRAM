package com.nc.edu.backend.service.impl;

import com.nc.edu.backend.converter.Converter;
import com.nc.edu.backend.domain.Post;
import com.nc.edu.backend.domain.User;
import com.nc.edu.backend.dto.PostDto;
import com.nc.edu.backend.dto.PostPageDto;
import com.nc.edu.backend.dto.UserDto;
import com.nc.edu.backend.dto.UserPageDto;
import com.nc.edu.backend.repository.UserRepository;
import com.nc.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Convert;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public List<User> findAll() {
        List<User> users = (List<User>) userRepository.findAll();

        return users;
    }

    public User findById(long id) {
        User user = userRepository.findById(id);

        return user;
    }

    public User findByLogin(String login) {
        User user = userRepository.findByLogin(login);

        return user;
    }

    public User save(User user, String fileStr, String fileName) {
        String decoded = new String(Base64.getDecoder().decode(fileStr));
        byte[] file = Base64.getDecoder().decode(fileStr);
        if (file != null) {
            File uploadDir = new File(uploadPath);//и перенести все в сервис?
            if (!uploadDir.exists()) {//еще раз спросить про реализацию раздачи картинки
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + fileName;//теперь не могу отобразить картинку
            try (FileOutputStream fos = new FileOutputStream(uploadPath + "/" + resultFilename)) {
                fos.write(file);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            user.setPhotoUrl(resultFilename);
        }
        return userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public void saveAll(Iterable<User> users) {
        userRepository.saveAll(users);
    }

    public List<Post> getUserPosts(Long id) {
        return userRepository.findById(id).get().getPosts();

    }

    public long count() {
        return userRepository.count();
    }

    @Override
    public UserDto getCountOfSubscribers(long userId) {
        UserDto userDto = new UserDto();
        userDto.setCountOfSubscribers(userRepository.findById(userId).getSubscribers().size());
        return userDto;
    }

    @Override
    public UserDto getCountOfSubscribtions(long userId) {
        UserDto userDto = new UserDto();
        userDto.setCountOfSubscribtions(userRepository.findById(userId).getSubscriptions().size());
        return userDto;
    }

    @Override
    public UserDto unsubscribe(int userId, int currentUserId) throws UserPrincipalNotFoundException {
        User user=findById(userId);
        User currentUser=findById(currentUserId);
        user.getSubscribers().remove(currentUser);
        userRepository.save(user);
        Converter converter=new Converter();
        UserDto userDto=converter.convertToUserDto(user);
        userDto.setSubscribed(false);
        return userDto;
    }

    @Override
    public UserDto subscribe(int userId, int currentUserId) throws UserPrincipalNotFoundException {
        User user=findById(userId);
        User currentUser=findById(currentUserId);
        user.getSubscribers().add(currentUser);
        userRepository.save(user);
        Converter converter=new Converter();
        UserDto userDto=converter.convertToUserDto(user);
        userDto.setSubscribed(true);
        return userDto;
    }

    @Override
    public UserPageDto getSubscriptions(long userId, Pageable pageable) throws UserPrincipalNotFoundException {
        Page<User> page = userRepository.findSubscriptions(userId,pageable);
        List<User> userList = page.getContent();
        List<UserDto> userDtos = new ArrayList<>();
        Converter converter = new Converter();
        UserDto userDto;
        for (User user : userList) {
            userDto=converter.convertToUserDto(user);
            userDtos.add(userDto);
        }
        return new UserPageDto(userDtos,
                pageable.getPageNumber(),
                page.getTotalPages());
    }
    @Override
    public UserPageDto getSubscribers(long userId, Pageable pageable) throws UserPrincipalNotFoundException {
        Page<User> page = userRepository.findSubscribers(userId,pageable);
        List<User> userList = page.getContent();
        List<UserDto> userDtos = new ArrayList<>();
        Converter converter = new Converter();
        UserDto userDto;
        for (User user : userList) {
            userDto=converter.convertToUserDto(user);
            userDtos.add(userDto);
        }
        return new UserPageDto(userDtos,
                pageable.getPageNumber(),
                page.getTotalPages());
    }

}
