package com.cnameproject.springboot;


import com.cnameproject.springboot.config.auth.SecurityConfig;
import com.cnameproject.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//RunWith는 즉, 스프링부트 테스트와 Jnit 사이에 연결자 역할
@RunWith(SpringRunner.class) //테스트를 진행할 때 Jnit에 내장된 실행자 외에 다른 실행자를 실행시킴. 여기서는 SpringRunner라는 스프링 실행자를 사용.
@WebMvcTest(controllers = HelloController.class,
    excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)}) //WebMvcTest는 web에 집중할 수 있는 어노테이션
//@WebMvcTest에서 스캔대상에서 SecurityConfig를 제거함.
public class HelloControllerTest {

    @Autowired //스프링이 관리하는 빈(Bean)을 주입 받음
    private MockMvc mvc; //웹API를 테스트할 때 사용함, 스프링 MVC테스트의 시작점, 이 클래스를 통해서 HTTP GET, POST 등에 대한 API테스트가 가능

    @WithMockUser(roles="USER")
    @Test
    public void helloReturn() throws Exception {
        String hello = "hello";

        //mvc.perform(get("/hello")) : MockMvc를 통해 /hello 주소로 HTTP GET을 요청함.
        //mvc.perform의 결과를 검증, HTTP Header의 Status를 검증, 흔히 200,404,500 등의 상태를 검증, 여기선 0K 즉 200인지 아니지를 검증
        //Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증함.
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @WithMockUser(roles="USER")
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto").param("name",name).param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
        //param : API 테스트할 때 사용될 요청 파라미터를 설정, 단 값은 String만 허용, 그래서 숫자/날짜 등의 데이터는 문자열로 변경해야만 함.
        //jsonPath : JSON응답값을 필드별로 검증할 수 있는 메소드, $를 기준으로 필드명을 명시함, 여기서는 name과 amount를 검즘.
    }
}
