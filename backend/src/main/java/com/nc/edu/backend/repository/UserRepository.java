package com.nc.edu.backend.repository;

import com.nc.edu.backend.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String login);
    User findById(long id);
}
