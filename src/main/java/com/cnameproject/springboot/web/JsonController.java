package com.cnameproject.springboot.web;

import com.cnameproject.springboot.service.posts.PostsService;
import com.cnameproject.springboot.web.dto.UserListDto;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class JsonController {

    private final PostsService postsService;

    @GetMapping("/view/info")
    public String viewInfo(){
        String post_list = new Gson().toJson(postsService.findAllDesc());
        return post_list;
    }

    @GetMapping("/view/post/{id}")
    public String viewPost(@PathVariable Long id) {
        String id_post_list = new Gson().toJson(postsService.findById(id));
        return id_post_list;
    }

//    @GetMapping("/now/user")
//    public String viewNowUser(@LoginUser SessionUser user){
//        String nowUserInfo = new Gson().toJson(user);
//        return nowUserInfo;
//    }

    @PostMapping("/user/login/google")
    public String saveUser (@RequestBody UserListDto userdto) {
        Map<String, Object> map = new HashMap();
        map.put("id", postsService.userSave(userdto));
        String id_list = new Gson().toJson(map);
        return id_list;
    }
}
