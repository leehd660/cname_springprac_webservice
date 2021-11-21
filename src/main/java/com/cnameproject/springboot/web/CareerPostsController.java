package com.cnameproject.springboot.web;

import com.cnameproject.springboot.service.posts.PostsService;
import com.cnameproject.springboot.web.dto.CareerUploadDto;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CareerPostsController {
    private final PostsService postsService;

    @PostMapping("/career/upload")
    public String careerUpload(@RequestBody CareerUploadDto cudto){
        Map<String, String> map = new HashMap();
        String a = postsService.careerSave(cudto);
        String b = String.valueOf(cudto.getUserId());
        map.put(b, a);
        String career_list = new Gson().toJson(map);
        return career_list;
    }
}
