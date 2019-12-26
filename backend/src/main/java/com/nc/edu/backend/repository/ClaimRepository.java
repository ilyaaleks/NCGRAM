package com.nc.edu.backend.repository;

import com.nc.edu.backend.domain.Claim;
import com.nc.edu.backend.domain.Comment;
import org.springframework.data.repository.CrudRepository;

public interface ClaimRepository  extends CrudRepository<Claim, Long> {
}
