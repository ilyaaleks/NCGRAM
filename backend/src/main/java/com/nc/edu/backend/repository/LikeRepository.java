package com.nc.edu.backend.repository;

import com.nc.edu.backend.domain.HashTag;
import com.nc.edu.backend.domain.LikeOrDislike;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LikeRepository extends CrudRepository<LikeOrDislike, Long> {
    @Query(value = "SELECT * FROM like_or_dislike l JOIN user u ON l.user_id=u.id JOIN posts p ON l.post_id=p.id  WHERE u.id=?2 AND p.id=?1", nativeQuery = true)
    LikeOrDislike findByPostId(long postId, long userId);
}
