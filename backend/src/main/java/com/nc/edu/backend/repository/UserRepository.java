package com.nc.edu.backend.repository;

import com.nc.edu.backend.domain.Post;
import com.nc.edu.backend.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String login);

    User findById(long id);

    @Query(value = "SELECT * FROM user u JOIN user_subscriptions us ON u.id = us.subscriber_id JOIN user u1 \n" +
            "  ON us.channel_id=u1.id WHERE us.channel_id=?1", nativeQuery = true)
    Page<User> findSubscriptions(long userId, Pageable page);

    @Query(value = "SELECT * FROM user u JOIN user_subscriptions us ON u.id = us.subscriber_id JOIN user u1 \n" +
            "  ON us.channel_id=u1.id WHERE us.channel_id=?1", nativeQuery = true)
    Page<User> findSubscribers(long userId, Pageable page);

}
