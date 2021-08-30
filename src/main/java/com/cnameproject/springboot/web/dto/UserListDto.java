package com.cnameproject.springboot.web.dto;

import com.cnameproject.springboot.domain.userinfo.user_info;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserListDto {
    private String name;
    private String email;
//    private Role role;

    @Builder
    public UserListDto(user_info entity){
        this.name= entity.getName();
        this.email = entity.getEmail();
//        this.role = entity.getRole();
    }

    public user_info toEntity(){
        return user_info.builder().name(name).email(email).build();
    }

}
