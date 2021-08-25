package com.cnameproject.springboot.domain.careerposts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CareerPostsRepository extends JpaRepository<CareerPosts,Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<CareerPosts> findAllDesc();
}
