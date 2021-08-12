package com.cnameproject.springboot.web.dto;

import com.cnameproject.springboot.domain.userinfo.UserInfo;
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
    public UserListDto(UserInfo entity){
        this.name= entity.getName();
        this.email = entity.getEmail();
//        this.role = entity.getRole();
    }

    public UserInfo toEntity(){
        return UserInfo.builder().name(name).email(email).build();
    }

}
