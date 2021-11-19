package com.cnameproject.springboot.web.dto;

import com.cnameproject.springboot.domain.userinfo.user_info;
import lombok.Getter;

@Getter
public class FindNearDto {
    private Long id;
    private String name;

    public FindNearDto(user_info entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
