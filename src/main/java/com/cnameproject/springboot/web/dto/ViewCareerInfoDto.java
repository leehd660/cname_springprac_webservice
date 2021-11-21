package com.cnameproject.springboot.web.dto;

import com.cnameproject.springboot.domain.careerposts.career_posts;
import lombok.Getter;

@Getter
public class ViewCareerInfoDto {
    private String careerCategory;
    private String careerTitle;
    private String careerStartDate;
    private String careerFinDate;

    public ViewCareerInfoDto(career_posts entity){
        this.careerCategory = entity.getCareer_category();
        this.careerTitle = entity.getCareer_title();
        this.careerStartDate = entity.getCareer_start_date();
        this.careerFinDate = entity.getCareer_fin_date();
    }
}
