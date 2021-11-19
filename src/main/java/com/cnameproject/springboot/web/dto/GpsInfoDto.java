package com.cnameproject.springboot.web.dto;

import com.cnameproject.springboot.domain.userinfo.user_info;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GpsInfoDto {
    private long getTime;
    private double latitude;
    private double longitude;

    @Builder
    public GpsInfoDto(user_info entity) {
        this.getTime = entity.getGetTime();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
    }

    public user_info toEntity(){
        return user_info.builder().getTime(getTime).latitude(latitude).longitude(longitude).build();
    }
}
