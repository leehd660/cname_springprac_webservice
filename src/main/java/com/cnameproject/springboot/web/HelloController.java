package com.cnameproject.springboot.web;

import com.cnameproject.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어 줌
public class HelloController {

    @GetMapping("/hello") //HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어 줌
    public String hello() { //이제 이 프로젝트는 /hello로 요청이 오면 문자열 hello를 반환하는 기능을 가지게 됨
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        //ResquestParam : 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션, 여기서는 외부에서 name이란 이름으로 넘긴 파라미터를 메소트 파라미터name에 저장
        return new HelloResponseDto(name,amount);
    }

}

