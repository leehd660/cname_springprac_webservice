package com.cnameproject.springboot.domain.friendinfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendInfoRepository extends JpaRepository<FriendInfo,Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<FriendInfo> findAllDesc();
}