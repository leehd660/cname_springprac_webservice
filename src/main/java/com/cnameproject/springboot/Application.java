package com.cnameproject.springboot; // 패키지명은 일반적으로 웹사이트 주소의 역순으로 함.

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //JPA Auditing활성화
@SpringBootApplication // 스프링부트의 자동 설정, 스프링 Bean 읽기와 성성을 모두 자동으로 설정, 이것은 항상 프로젝트의 최상단에 위치해야함
public class Application { //프로젝트의 메인클래스
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args); //SpringApplication.run으로 인해 내장 WAS(웹어플리케이션 서버)를 실행
    }
}
