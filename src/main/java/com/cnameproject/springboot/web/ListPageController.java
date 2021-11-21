package com.cnameproject.springboot.web;

import com.cnameproject.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ListPageController {

    private final PostsService postsService;

    @GetMapping("/view/friend/{id}")
    public List<String> viewList(@PathVariable Long id) {
        //id는 내 아이디
        List<String> answer = postsService.viewFriendList(id);
        return answer;
    }
}
