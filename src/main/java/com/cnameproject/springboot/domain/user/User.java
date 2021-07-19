package com.cnameproject.springboot.domain.user;

import com.cnameproject.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    //사용자 정보를 담당할 도메인을 생성. Entity클래스로 새로운 테이블을 형성하는건가?

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    //JPA로 데이터베이스로 저장할 때 Enum 값을 어떤 형태로 저장할지를 결정함.
    //기본적으로는 int로 된 숫자가 저장됨. 숫자로 저장되면 데이터베이스에서 그 값이 무슨 코드인지 알 수가 없음
    //따라서 EnumType.STRING을 선언하여 문자열로 저장될 수 있도록 함.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
