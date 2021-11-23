package com.cnameproject.springboot.web.dto;

import com.cnameproject.springboot.domain.careerposts.career_posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CareerUploadDto {
    private String career_category;
    private String career_title;
    private String career_start_date;
    private String career_fin_date;
    private String career_content;
    private String career_picture;
    private Long user_id;

    @Builder
    public CareerUploadDto(career_posts entity){
        this.career_category = entity.getCareer_category();
        this.career_title = entity.getCareer_title();
        this.career_start_date = entity.getCareer_start_date();
        this.career_fin_date = entity.getCareer_fin_date();
        this.career_content = entity.getCareer_content();
        this.career_picture = entity.getCareer_picture();
        this.user_id = entity.getUser_id();
    }

    public career_posts toEntity(){
        return career_posts.builder().careerCategory(career_category).careerTitle(career_title).
                careerStartDate(career_start_date).careerFinDate(career_fin_date).careerContent(career_content).
                careerPicture(career_picture).userId(user_id).build();
    }

}
