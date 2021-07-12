package com.cnameproject.springboot.domain.posts;

import com.cnameproject.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //클래스 내 모든 필드의 getter메소드 자동생성
@NoArgsConstructor //기본 생성자 자동추가, public Posts(){}와 같은 역할
@Entity // 테이블과 링크될 클래스임을 나타냄,
public class Posts extends BaseTimeEntity { //Posts 클래스는 실제 DB의 테이블과 연결되는 테이블로 흔히 Entity 클래스라고 함.

    //Entity클래스에는 절대 Setter메소드를 생성하지 않음 : 해당 클래스의 인스턴스값들이 언제 어디서 변해야하는지 코드상으로 명확하게 구분할 수 X.
    //대신 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도롤 나타낼 수 있는 메소드를 추가해야만 함.

    @Id // 해당테이블의 PK필드를 나타냄.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK의 생성 규칙. GenrationType.IDENTITY 옵션을 추가해야만 auto_increment가 됨.
    private Long id;

    //Column 테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 됨.
    //문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나, 타입을 TEXT로 변경하고 싶거나 등의 경우에 사용됨
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    //어떻게 DB에 값을 채워 insert하는가? : 기본적인 구조로는 생성자를 통해 최종값을 채운 후 DB에 삽입 insert하는 것.
    //값 변경이 필요한 경우 해당 이벤트에 맞는 public메소드를 호출하여 변경하는 것을 전제로 함.
    //하지만 우리는 Builder를 이용함. 생성자나 Builder나 생성 시점에 값을 채워주는 역할은 똑같음.
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
