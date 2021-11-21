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
        this.careerCategory = entity.getCareerCategory();
        this.careerTitle = entity.getCareerTitle();
        this.careerStartDate = entity.getCareerStartDate();
        this.careerFinDate = entity.getCareerFinDate();
        this.careerContent = entity.getCareerContent();
        this.careerPicture = entity.getCareerPicture();
        this.userId = entity.getUserId();
    }

    public career_posts toEntity(){
        return career_posts.builder().careerCategory(careerCategory).careerTitle(careerTitle).
                careerStartDate(careerStartDate).careerFinDate(careerFinDate).careerContent(careerContent).
                careerPicture(careerPicture).userId(userId).build();
    }

}
