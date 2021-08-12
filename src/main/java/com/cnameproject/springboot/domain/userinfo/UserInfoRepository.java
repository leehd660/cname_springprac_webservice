package com.cnameproject.springboot.domain.userinfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    @Query("SELECT ui FROM UserInfo ui ORDER BY ui.id DESC")
    List<UserInfo> findAllDesc();
}
