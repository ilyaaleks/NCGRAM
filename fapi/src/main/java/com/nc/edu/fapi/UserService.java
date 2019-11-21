package com.nc.edu.fapi;

import com.nc.edu.fapi.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    private ArrayList<User> users=new ArrayList<User>();
    private void addUser(User user)
    {
        users.add(user);
    }
    private void deleteUser(User user)
    {
        users.remove(user);
    }
}
