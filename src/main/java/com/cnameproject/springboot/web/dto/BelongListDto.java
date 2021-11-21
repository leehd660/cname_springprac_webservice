package com.cnameproject.springboot.web.dto;

import com.cnameproject.springboot.domain.belonginfo.belong_info;
import lombok.Getter;

@Getter
public class BelongListDto {
    private Long id;
    //    private String name;
    private String belong_data;
    private String position_data;

    public BelongListDto(belong_info entity) {
        this.id = entity.getUser_id();
        this.belong_data = entity.getBelong_data();
        this.position_data = entity.getPosition_data();
    }
}
