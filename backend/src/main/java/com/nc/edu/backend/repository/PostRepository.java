package com.nc.edu.backend.repository;

import com.nc.edu.backend.domain.Post;
import com.nc.edu.backend.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
    Page<Post> findAll(Pageable pageable);
    Page<Post> findByAuthorId(long userId, Pageable page);
    Page<Post> findByAuthor_Subscriptions(User user, Pageable pageable);
    @Query(value = "select * from posts p join user u on p.user_id=u.id where u.id = ?1",nativeQuery = true)
    Page<Post> findByAuthor(long userId, Pageable page);
    Post findById(long id);
}
