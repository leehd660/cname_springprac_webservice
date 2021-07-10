package com.cnameproject.springboot;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.cnameproject.springboot.web.HelloController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//RunWith는 즉, 스프링부트 테스트와 Jnit 사이에 연결자 역할
@RunWith(SpringRunner.class) //테스트를 진행할 때 Jnit에 내장된 실행자 외에 다른 실행자를 실행시킴. 여기서는 SpringRunner라는 스프링 실행자를 사용.
@WebMvcTest(controllers = HelloController.class) //WebMvcTest는 web에 집중할 수 있는 어노테이션
public class HelloControllerTest {

    @Autowired //스프링이 관리하는 빈(Bean)을 주입 받음
    private MockMvc mvc; //웹API를 테스트할 때 사용함, 스프링 MVC테스트의 시작점, 이 클래스를 통해서 HTTP GET, POST 등에 대한 API테스트가 가능

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
}
