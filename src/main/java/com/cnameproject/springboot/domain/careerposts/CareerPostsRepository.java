package com.cnameproject.springboot.domain.careerposts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CareerPostsRepository extends JpaRepository<career_posts,Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<career_posts> findAllDesc();

    @Query(value = "SELECT cp.id FROM career_posts cp where cp.user_id =:userId", nativeQuery = true)
    Long findByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM career_posts cp where cp.user_id =:userId", nativeQuery = true)
    List<career_posts> findViewInfoById(@Param("userId") Long userId);

}
