package com.cnameproject.springboot.web.dto;

import com.cnameproject.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    // Entity클래스(Posts 클래스)와 거의 유사한 형태임에도 Dto클래스르 추가로 생성함.
    // 하지만, 절대로 Entity클래스를 Request/Response클래스로 사용하면 X
    //그 이유 : Entity클래스는 데이터베이스와 맞닿는 핵심 클래스임. 반면 Request와 Response용 Dto는 View를위한 클래스라 정말 자주 변경 필요

    private  String title;
    private  String content;
    private  String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder().title(title).content(content).author(author).build();
    }
}
