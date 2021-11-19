package com.cnameproject.springboot.web.dto;

import com.cnameproject.springboot.domain.userinfo.user_info;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddFriendIdDto {
    private Long id;

    @Builder
    public AddFriendIdDto(user_info entity) {
        this.id = entity.getId();
    }

    public user_info toEntity(){
        return user_info.builder().friend_id(String.valueOf(id)).build();
    }
}
