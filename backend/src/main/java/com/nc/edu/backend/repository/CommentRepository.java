package com.nc.edu.backend.repository;

import com.nc.edu.backend.domain.Comment;
import com.nc.edu.backend.domain.Post;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository  extends CrudRepository<Comment, Long> {
}
