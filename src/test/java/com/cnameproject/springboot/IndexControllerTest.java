package com.cnameproject.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IndexControllerTest {
    //실제로 URL 호출 시 페이지의 내용이 제대로 호출되는지에 대한 테스트
    //HTML도 결국 규칙이 있는 문자열

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void 메인페이지_로딩() {
        //when
        // TestRestTemplate를 통해 "/"로 호출하였을 때 index.mustache에 포함된 코드들이 있는지 확인하는 것.
        String body = this.restTemplate.getForObject("/", String.class);

        //then
        // "스프링 부트로 시작하는 웹 서비스"문자열이 포함되어있는지 비교하는 것.
        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");
    }
}
