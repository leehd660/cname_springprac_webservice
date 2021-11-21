package com.cnameproject.springboot.web.dto;

import com.cnameproject.springboot.domain.userinfo.user_info;
import lombok.Getter;

@Getter
public class ViewUserInfoDto {
    private String name;
    private String email;
    private String phone_num;

    public ViewUserInfoDto(user_info entity){
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.phone_num = entity.getPhone_num();
    }
}
