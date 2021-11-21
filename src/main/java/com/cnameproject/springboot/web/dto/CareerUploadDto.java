package com.cnameproject.springboot.web.dto;

import com.cnameproject.springboot.domain.careerposts.career_posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CareerUploadDto {
    private String careerCategory;
    private String careerTitle;
    private String careerStartDate;
    private String careerFinDate;
    private String careerContent;
    private String careerPicture;
    private Long userId;

    @Builder
    public CareerUploadDto(career_posts entity){
        this.careerCategory = entity.getCareer_category();
        this.careerTitle = entity.getCareer_title();
        this.careerStartDate = entity.getCareer_start_date();
        this.careerFinDate = entity.getCareer_fin_date();
        this.careerContent = entity.getCareer_content();
        this.careerPicture = entity.getCareer_picture();
        this.userId = entity.getUser_id();
    }

    public career_posts toEntity(){
        return career_posts.builder().careerCategory(careerCategory).careerTitle(careerTitle).
                careerStartDate(careerStartDate).careerFinDate(careerFinDate).careerContent(careerContent).
                careerPicture(careerPicture).userId(userId).build();
    }

}
