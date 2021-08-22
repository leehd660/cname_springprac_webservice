package com.cnameproject.springboot.web.dto;

import com.cnameproject.springboot.domain.userinfo.UserInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserAddInfoDto {
    private String name;
    private String phone_num;
    private String bluetooth_data;
    private String birth_data;

    @Builder
    public UserAddInfoDto(UserInfo entity){
        this.name = entity.getName();
        this.phone_num = entity.getPhone_num();
        this.bluetooth_data = entity.getBluetooth_data();
        this.birth_data = entity.getBirth_data();
    }

    public UserInfo toEntity(){
        return UserInfo.builder().name(name).phone_num(phone_num).bluetooth_data(bluetooth_data).birth_data(birth_data).build();
    }
}
