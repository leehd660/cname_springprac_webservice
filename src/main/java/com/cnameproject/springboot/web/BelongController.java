package com.cnameproject.springboot.web;

import com.cnameproject.springboot.service.posts.PostsService;
import com.cnameproject.springboot.web.dto.BelongSaveDto;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BelongController {
    //소속,직책과 관련된 모든 controller

    private final PostsService postsService;

    @PostMapping("/belong/save")
    public String saveJob (@RequestBody BelongSaveDto belongSaveDto){
        //안드로이드에서 user_id까지 json 파일에 넣어서 보내줘야함.
        boolean answer = false;
        long user_id = belongSaveDto.getUser_id();
        long saveId = postsService.belongSave(belongSaveDto);
        if (user_id == saveId){
            answer = true;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("result", answer);
        String result = new Gson().toJson(map);
        return result;
    }
}
