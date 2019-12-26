package com.nc.edu.backend.repository;

import com.nc.edu.backend.domain.Comment;
import com.nc.edu.backend.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository  extends CrudRepository<Comment, Long> {
    @Query(value = "SELECT * FROM comment c JOIN posts p ON c.post_id=p.id WHERE p.id=?1", nativeQuery = true)
    Page<Comment> findByPostId(long postId, Pageable page);
}
