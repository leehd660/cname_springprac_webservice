package com.cnameproject.springboot.domain.careerposts;

import com.cnameproject.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class career_posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String career_category;

    @Column(length = 500, nullable = false)
    private String career_title;

    @Column(nullable = false)
    private String career_start_date;

    @Column
    private String career_fin_date;

    @Column(length = 500, columnDefinition = "TEXT")
    private String career_content;

    @Column
    private String career_picture;

    @Column
    private Long career_friend;

    @JoinColumn
    private Long userId;


    @Builder
    public career_posts(String careerCategory, String careerTitle, String careerStartDate, String careerFinDate, String careerContent, String careerPicture, Long careerFriend, Long userId) {
        this.career_category=careerCategory;
        this.career_title=careerTitle;
        this.career_start_date=careerStartDate;
        this.career_fin_date=careerFinDate;
        this.career_content=careerContent;
        this.career_picture=careerPicture;
        this.career_friend=careerFriend;
        this.userId=userId;
    }

    public void update(String careerCategory, String careerTitle,String careerStartDate,String careerFinDate,String careerContent,String careerPicture, Long careerFriend) {
        this.career_category=careerCategory;
        this.career_title=careerTitle;
        this.career_start_date=careerStartDate;
        this.career_fin_date=careerFinDate;
        this.career_content=careerContent;
        this.career_picture=careerPicture;
        this.career_friend=careerFriend;
    }
}
