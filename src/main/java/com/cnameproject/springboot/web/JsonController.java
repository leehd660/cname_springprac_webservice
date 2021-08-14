package com.cnameproject.springboot.web;

import com.cnameproject.springboot.service.posts.PostsService;
import com.cnameproject.springboot.web.dto.UserListDto;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public void saveUser (@RequestBody UserListDto userdto) {
        String str = "";

        postsService.userSave(userdto);
    }
}