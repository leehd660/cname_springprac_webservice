package com.cnameproject.springboot.domain.userinfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserInfoRepository extends JpaRepository<User_info, Long> {

    @Query("SELECT ui FROM User_info ui ORDER BY ui.id DESC")
    List<User_info> findAllDesc();

    @Query(value = "SELECT id FROM User_info ui where email = :email", nativeQuery = true)
    Long findIdByEmail(@Param("email") String findEmail);

    @Query(value = "SELECT * FROM User_info ui where ui.id = :id", nativeQuery = true)
    User_info findInfoByID(@Param("id") Long id);
}
