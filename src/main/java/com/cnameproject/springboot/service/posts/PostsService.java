package com.cnameproject.springboot.service.posts;

import com.cnameproject.springboot.domain.posts.PostsRepository;
import com.cnameproject.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

// 스프링에서 Bean을 주입받는 방식 : @Autowired , setter, 생성자 > 이중 생성자로 주입받는 것을 가장 권장
// final이 선언된 모든 필드를 인자값으로 하는 생성자를 룸복의 @RequiredArgsConstructor가 대신 생성해준 것.
// 롬복 어노테이션을 사용한 이유 : 해당 클래스의 의존성 관계가 변경될 때마다 생성자 코드를 계속해서 수정하는 번거로음 해결을 위해
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}
