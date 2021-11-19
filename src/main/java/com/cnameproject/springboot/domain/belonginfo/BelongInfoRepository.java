package com.cnameproject.springboot.domain.belonginfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BelongInfoRepository extends JpaRepository<belong_info,Long> {

    @Query("SELECT bi FROM belong_info bi ORDER BY bi.user_id DESC")
    List<belong_info> findAllDesc();

}
