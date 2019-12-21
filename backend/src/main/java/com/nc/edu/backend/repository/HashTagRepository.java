package com.nc.edu.backend.repository;

import com.nc.edu.backend.domain.HashTag;
import com.nc.edu.backend.domain.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface HashTagRepository extends CrudRepository<HashTag, Long> {
    HashTag findByText(String hashTag);
}
