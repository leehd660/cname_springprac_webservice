package com.cnameproject.springboot.web.dto;

import com.cnameproject.springboot.domain.belonginfo.belong_info;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BelongSaveDto {
    private long user_id;
    private String belong_data;
    private String position_data;
    private String tel_data;
    private String fax_data;
    private String start_data;
    private String fin_data;

    @Builder
    public BelongSaveDto(belong_info entity){
        this.user_id=user_id;
        this.belong_data= entity.getBelong_data();
        this.position_data= entity.getPosition_data();
        this.tel_data= entity.getTel_data();
        this.fax_data= entity.getFax_data();
        this.start_data= entity.getStart_data();;
        this.fin_data= entity.getFin_data();;
    }

    public belong_info toEntity(){
        return belong_info.builder().user_id(user_id).belong_data(belong_data).position_data(position_data).tel_data(tel_data).fax_data(fax_data).start_data(start_data).fin_data(fin_data).build();
    }
}
