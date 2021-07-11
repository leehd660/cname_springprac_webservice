package com.cnameproject.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

//보통 ibatis나 MyBatis 등에서 Dao라고 불리는 DB Layer접근자인데
//JPA에선 Repository라고 부르며 인터페이스로 생성함.
//단순히 인터페이스 생성 후,JpaRepository<Entity 클래스, PK타입>을 상속하면 기본적인 CRUD메소드가 자동으로 생성됨.
//주의해야할 점 : Entity클래스와 기본 Entity Repository는 함께 위치해야 함.
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
