//package com.programing.tech.oauth2;
//
//import com.programing.tech.entity.Provider;
//
//import java.util.Map;
//
//public class Oauth2UserDetailFactory {
//
//
//    public static OauthUserDetails getOauth2UserDetails(String registration, Map<String, Object> attribute) {
//        if (registration.equals(Provider.google.name())) {
//            return new Oauth2UserGoogle(attribute);
//        }else if (registration.equals(Provider.github.name())) {
//            return new Oauth2UserGitHub(attribute);
//        }else if (registration.equals(Provider.facebook.name())){
//            return new Oauth2UserFaceBook(attribute);
//        } else
//        return null;
//    }
//}