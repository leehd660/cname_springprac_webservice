package com.cnameproject.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
        //build.gradle에서 설치한 머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정됨.
        //앞의 경로는 src/main/resources/templates로, 뒤의 파일 확장자는 .mustache가 붙음.
        //즉, 여기서는 index를 반환하기 때문에, src/main/resources/templates/index.mustache로 전환되어 View Resolver가 처리하게 됨.
        //ViewResolver는 URL요청의 결과를 전달할 타입과 값을 지정하는 관리자 격으로 볼 수 있음.
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
