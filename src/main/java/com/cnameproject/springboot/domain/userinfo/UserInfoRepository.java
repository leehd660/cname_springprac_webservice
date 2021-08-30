package com.cnameproject.springboot.domain.userinfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserInfoRepository extends JpaRepository<user_info, Long> {

    @Query("SELECT ui FROM user_info ui ORDER BY ui.id DESC")
    List<user_info> findAllDesc();

    @Query(value = "SELECT id FROM user_info ui where email = :email", nativeQuery = true)
    Long findIdByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM user_info ui where ui.id = :id", nativeQuery = true)
    user_info findInfoByID(@Param("id") Long id);
}
