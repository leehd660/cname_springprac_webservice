package com.cnameproject.springboot.service.posts;

import com.cnameproject.springboot.domain.belonginfo.BelongInfoRepository;
import com.cnameproject.springboot.domain.belonginfo.belong_info;
import com.cnameproject.springboot.domain.careerposts.CareerPostsRepository;
import com.cnameproject.springboot.domain.friendinfo.FriendInfoRepository;
import com.cnameproject.springboot.domain.posts.Posts;
import com.cnameproject.springboot.domain.posts.PostsRepository;
import com.cnameproject.springboot.domain.userinfo.UserInfoRepository;
import com.cnameproject.springboot.domain.userinfo.user_info;
import com.cnameproject.springboot.web.dto.*;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 스프링에서 Bean을 주입받는 방식 : @Autowired , setter, 생성자 > 이중 생성자로 주입받는 것을 가장 권장
// final이 선언된 모든 필드를 인자값으로 하는 생성자를 룸복의 @RequiredArgsConstructor가 대신 생성해준 것.
// 롬복 어노테이션을 사용한 이유 : 해당 클래스의 의존성 관계가 변경될 때마다 생성자 코드를 계속해서 수정하는 번거로음 해결을 위해
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final UserInfoRepository userInfoRepository;
    private final BelongInfoRepository belongInfoRepository;
    private final FriendInfoRepository friendInfoRepository;
    private final CareerPostsRepository careerPostsRepository;


    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        //update기능에 쿼리를 날리는 부분이 없음 > 이유 : JPA의 영속성 컨텍스트 때문
        //영속성 컨텍스트란, 엔티티를 영구 저장하는 환경
       Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        posts.update(requestDto.getTitle(),requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        postsRepository.delete(posts); //JpaRepository에서 이미 delete메소드를 지원하고 있으니 이를 활용
        //엔티티를 파라미터로 삭제할 수도 있고, deleteById 메소드를 이용하면 id로 삭제할 수도 있음
        //존재하는 Posts인지 확인을 위해 엔티티 조회 후 그대로 삭제함.
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    //트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선되기 때문에
    //등록, 수정, 삭제 기능이 전혀 업는 서비스 메소드에서 사용하는 것을 추천함.
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
        //.map(PostsListResponseDto::new)의 식은 실제로 .map(posts -> new PostsListResponseDto(posts))와 같음
        //postsRepository결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto 변환 -> List로 반환하는 메소드
    }

    @Transactional
    public String userSave(UserListDto userListDto) {
        boolean newId = false;
        String findEmail = userListDto.getEmail();
        Long findId = userInfoRepository.findIdByEmail(findEmail);
        if (findId == null){
            findId = userInfoRepository.save(userListDto.toEntity()).getId();
            newId = true;
        }
//        Long findId = userInfoRepository.save(userListDto.toEntity()).getId();
        return Long.toString(findId) + " " + newId;
    }

    @Transactional
    public String userAddUpdate(Long id, UserAddInfoDto userAddInfoDto){
        user_info userInfo = userInfoRepository.findInfoByID(id);

        userInfo.addUpdate(userAddInfoDto.getName(),userAddInfoDto.getPhone_num(),userAddInfoDto.getBluetooth_data(),userAddInfoDto.getBirth_data());
        return Long.toString(id);
    }

    @Transactional
    public String gpsAddUpdate(Long id, GpsInfoDto gpsInfoDto){
        user_info userInfo = userInfoRepository.findInfoByID(id);
//        long realTime = 0;
//        String time = gpsInfoDto.getGetTime();
//        String[] timeArr = time.split(":");
//        for (int i=0; i<3; i++){
//            realTime += (long) Math.pow(60,2-i) * Long.parseLong(timeArr[i]);
//        }
        userInfo.cname_update(gpsInfoDto.getGet_time(), gpsInfoDto.getLatitude(), gpsInfoDto.getLongitude());
        return String.valueOf(id);
    }

    @Transactional
    public List<FindNearDto> findNearId(Long id, Long get_time, double latitude, double longitude) {
        //findNearId를 하면 user info테이블을 쭉 탐색해서 시간, 위도,경도 value가 오차 범위 안에 있는 것을 전부 뽑는다.
        //결과 값은 findNearDto들의 리스트를 반환한다.
//        List<User_info> userinfoArr = userInfoRepository.findAllDesc();
//        String[] timeArr = getTime.split(":");
//        for (String t : timeArr){
//
//        }
        long upTime = get_time + 8;
        long downTime = get_time - 8;
        double upLatitude = latitude+0.0002;
        double downLatitude = latitude-0.0002;
        double upLongitude = longitude+0.0002;
        double downLongitude = longitude-0.0002;

//        for (User_info i : userinfoArr){
//            double testLat = i.getLatitude();
//            double testlong = i.getLongitude();
//            ;
//            if ()
//        }
        return userInfoRepository.findNearIdByCname(upTime,downTime,upLatitude, downLatitude, upLongitude, downLongitude, id)
                .stream().map(FindNearDto::new).collect(Collectors.toList());

    }

    @Transactional
    public Long belongSave(BelongSaveDto belongSaveDto){
        Long saveId = belongInfoRepository.save(belongSaveDto.toEntity()).getUser_id();
        //여기서 saveId는 userId임.
        return saveId;
    }

    @Transactional
    public String addFriend(long id, AddFriendIdDto addFriendIdDto) {
        user_info userInfo = userInfoRepository.findInfoByID(addFriendIdDto.getId());
        userInfo.addFriendId(id);
        return String.valueOf(id);
    }

    @Transactional
    public String careerSave(CareerUploadDto careerUploadDto){
        Long findId = careerPostsRepository.save(careerUploadDto.toEntity()).getId();
        return Long.toString(findId);
    }

    @Transactional
    public List<String> viewFriendList(long userId) {
        List<String> jsonReturnList = new ArrayList<>();
        String friendIdStr = userInfoRepository.findInfoByID(userId).getFriend_id();
        String[] friendStrArr = friendIdStr.split("/");
        for (String friendStr : friendStrArr) {
            long findFriendId = Long.parseLong(friendStr);
            Map<String,String> map = new HashMap<>();
            map.put("id",String.valueOf(findFriendId)); //id넣어주기
            String friendName = userInfoRepository.findInfoByID(findFriendId).getName();
            map.put("name",friendName); //name넣어주기
            belong_info belong_info = belongInfoRepository.findInfoByID(findFriendId);
            String belong_data = belong_info.getBelong_data();
            String position_data = belong_info.getPosition_data();
            map.put("belong_data", belong_data);
            map.put("position_data", position_data);

            String mapToJson = new Gson().toJson(map);
            jsonReturnList.add(mapToJson);
        }
        return jsonReturnList;
    }

    @Transactional
    public String viewMyNamecard(Long id){
        Map<String, List> map = new HashMap<>();
        List<ViewUserInfoDto> userInfoList = userInfoRepository.findViewInfoByID(id)
                .stream().map(ViewUserInfoDto::new).collect(Collectors.toList());
        map.put("user_info", userInfoList);
        List<ViewBelongInfoDto> belongInfoList = belongInfoRepository.findViewInfoByID(id)
                .stream().map(ViewBelongInfoDto::new).collect(Collectors.toList());
        map.put("belong_info",belongInfoList);
        List<ViewCareerInfoDto> careerInfoList = careerPostsRepository.findViewInfoById(id)
                .stream().map(ViewCareerInfoDto::new).collect(Collectors.toList());
        map.put("career_info",careerInfoList);

        String mapToJson = new Gson().toJson(map);
        return mapToJson;
    }
}
