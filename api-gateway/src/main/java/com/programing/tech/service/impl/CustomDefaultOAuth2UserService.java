//package com.programing.tech.service.impl;
//
//import com.programing.tech.entity.Role;
//import com.programing.tech.entity.User;
//import com.programing.tech.oauth2.Oauth2UserDetailFactory;
//import com.programing.tech.oauth2.OauthUserDetails;
//import com.programing.tech.repository.RoleRepository;
//import com.programing.tech.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Component;
//
//import java.util.*;
//
//@Component
//@RequiredArgsConstructor
//public class CustomDefaultOAuth2UserService extends DefaultOAuth2UserService {
//
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2User oAuth2User = super.loadUser(userRequest);
//        checkKingOauth2User(userRequest,oAuth2User);
//        return oAuth2User;
//    }
//
//    private void checkKingOauth2User(OAuth2UserRequest oAuth2UserRequest,OAuth2User oAuth2User) {
//        OauthUserDetails oauthUserDetails = Oauth2UserDetailFactory.getOauth2UserDetails(oAuth2UserRequest.getClientRegistration().getRegistrationId(),oAuth2User.getAttributes());
//        if (oauthUserDetails==null) {
//            throw new RuntimeException("Not exits properties in info user Login");
//        }
//        Optional<User> userOpt = userRepository.findByUsernameAndProviderId(oauthUserDetails.getEmail(),oAuth2UserRequest.getClientRegistration().getRegistrationId());
//
//        User user;
//        if (userOpt.isPresent()) {
//            user=userOpt.get();
//
//            if (!user.getProviderId().equals(oAuth2UserRequest.getClientRegistration().getRegistrationId())) {
//                throw new RuntimeException("Invalid site login "+oAuth2UserRequest.getClientRegistration().getRegistrationId());
//            }
//
//        } else {
//            user = registerUser(oauthUserDetails,oAuth2UserRequest);
//        }
//
////        return OAuth2UserDetailCustom.builder().user(user).attributes(oAuth2UserRequest.getAdditionalParameters());
//
//    }
//
//    private User registerUser(OauthUserDetails oauthUserDetails,OAuth2UserRequest oAuth2UserRequest) {
//        Role role = roleRepository.findRoleByName("ROLE_USER").orElse(null);
//        User user = User.builder()
//                .username(oauthUserDetails.getEmail())
//                .providerId(oAuth2UserRequest.getClientRegistration().getRegistrationId())
//                .enabled(true)
//                .accountNonExpired(true)
//                .accountNonLocked(true)
//                .credentialsNonExpired(true)
//                .roles(Arrays.asList(role)).build();
//
//        return userRepository.save(user);
//
//    }
//}
