package com.nc.edu.backend.service.impl;

import com.nc.edu.backend.domain.Post;
import com.nc.edu.backend.domain.User;
import com.nc.edu.backend.repository.UserRepository;
import com.nc.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public User findById(long id)
    {
        return userRepository.findById(id);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public void saveAll(Iterable<User> users)
    {
        userRepository.saveAll(users);
    }
    public List<Post> getUserPosts(Long id)
    {
        return userRepository.findById(id).get().getPosts();

    }
    public long count(){
        return userRepository.count();
    }


}
