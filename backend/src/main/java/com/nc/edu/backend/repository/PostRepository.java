package com.nc.edu.backend.repository;

import com.nc.edu.backend.domain.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
