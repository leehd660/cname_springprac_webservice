package com.cnameproject.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //클래스 내 모든 필드의 getter메소드 자동생성
@NoArgsConstructor //기본 생성자 자동추가, public Posts(){}와 같은 역할
@Entity // 테이블과 링크될 클래스임을 나타냄,
public class Posts { //Posts 클래스는 실제 DB의 테이블과 연결되는 테이블로 흔히 Entity 클래스라고 함.

    @Id // 해당테이블의 PK필드를 나타냄.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK의 생성 규칙. GenrationType.IDENTITY 옵션을 추가해야만 auto_increment가 됨.
    private Long id;

    //Column 테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 됨.
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
