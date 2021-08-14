//package com.cnameproject.springboot.config.auth;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    private final LoginUserArgumentResolver loginUserArgumentResolver;
//
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        //HandlerMethodArgumentResolver는 항상 WebMvcConfigurer의 addArgumentResolver()를 통해 추가해야함.
//        //다른 HandlerMethodArgumentResolver가 필요하다면 같은 방식으로 추가해주면 됨.
//        argumentResolvers.add(loginUserArgumentResolver);
//    }
//}
