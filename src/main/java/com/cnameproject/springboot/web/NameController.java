package com.cnameproject.springboot.web;

import com.cnameproject.springboot.service.posts.PostsService;
import com.cnameproject.springboot.web.dto.AddFriendIdDto;
import com.cnameproject.springboot.web.dto.FindNearDto;
import com.cnameproject.springboot.web.dto.GpsInfoDto;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class NameController {

    private final PostsService postsService;

    @PutMapping("/test/addGPS/{id}")
    public String addGPSdata (@PathVariable Long id, @RequestBody GpsInfoDto gpsInfoDto){
        String myId = postsService.gpsAddUpdate(id, gpsInfoDto);
        Map<String, String> map = new HashMap<>();
        map.put("testId",myId);
        String answer = new Gson().toJson(map);
        return answer;
    }


    @PutMapping("/exchange/card/{id}")
    public String exchangeCardId(@PathVariable Long id, @RequestBody GpsInfoDto gpsInfoDto) {
        //id는 자기 자신의 id.

//        long realTime = 0;
//        String time = gpsInfoDto.getGetTime();
//        String[] timeArr = time.split(":");
//        for (int i=0; i<3; i++){
//            realTime += (long) Math.pow(60,2-i) * Long.parseLong(timeArr[i]);
//        }
        String myId = postsService.gpsAddUpdate(id, gpsInfoDto); //시간, gps데이터 저장
        String exchangeList = "";
        long beforeTime = System.currentTimeMillis();
        long nowTime = 0;
        double betweenTime = 0;
        while (true) {
            List<FindNearDto> nearFreindList = postsService.findNearId(id, gpsInfoDto.getGet_time(), gpsInfoDto.getLatitude(),gpsInfoDto.getLongitude());
            exchangeList = new Gson().toJson(nearFreindList);
            if (((System.currentTimeMillis()-beforeTime)/1000)>=8){
                break;
            }
        }
        return exchangeList;
    }

    @PutMapping("/add/friend/{id}")
    public String addFriendId(@PathVariable long id, @RequestBody AddFriendIdDto addFriendIdDto) {
        //{id} : friend id, id in dto : my id

        String saveResult = postsService.addFriend(id, addFriendIdDto);
        return saveResult;
    }
}
