//package com.cnameproject.springboot.config.auth;
//
//
//import com.cnameproject.springboot.config.auth.dto.OAuthAttributes;
//import com.cnameproject.springboot.config.auth.dto.SessionUser;
//import com.cnameproject.springboot.domain.user.User;
//import com.cnameproject.springboot.domain.user.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpSession;
//import java.util.Collections;
//
//@RequiredArgsConstructor
//@Service
//public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
//    private final UserRepository userRepository;
//    private final HttpSession httpSession;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2UserService delegate = new DefaultOAuth2UserService();
//        OAuth2User oAuth2User = delegate.loadUser(userRequest);
//
//        String registrationId = userRequest.
//                getClientRegistration().getRegistrationId(); // registrationID: 현재 로그인 진행중인 서비스를 구분하는 코드. 지금은 구글만 사용하는 불필요한 값이지만, 이후 네이버 로그인 연동 시에 네이버로 로그인인지, 구글로 로그인인지 구분하기 위해서 사용함.
//        String userNameAttributeName = userRequest.
//                getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
//        //userNameAttributeName : OAuth2 로그인 진행 시 키가 되는 필드값을 이야기함. Primary key와 같은 의미
//        //구글의 경우 기본적으로 코드를 지원하지만, 네이버 카카오 등은 기본 지원하지 않습니다. 구글의 기본 코드는 "sub"입니다.
//
//        //OAuthAttributes : OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스/ 이후 네이버 등 다른 소셜 로그인도 이 클래스를 사용함.
//        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName,oAuth2User.getAttributes());
//
//        User user = saveOrUpdate(attributes);
//
//        httpSession.setAttribute("user", new SessionUser(user));
//        //SessionUser:세션에 사용자 정보를 저장하기 위한 Dto클래스.
//
//        return new DefaultOAuth2User(
//                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
//                attributes.getAttributes(),
//                attributes.getNameAttributeKey());
//    }
//
//    private User saveOrUpdate(OAuthAttributes attributes) {
//        //구글 사용자 정보가 업데이트 되었을 때를 대비하여 update기능도 같이 구현 (name, picture등이 변경되면 User 엔티티에도 반영됨.)
//        User user = userRepository.findByEmail(attributes.getEmail())
//                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
//                .orElse(attributes.toEntity());
//
//        return userRepository.save(user);
//    }
//}
