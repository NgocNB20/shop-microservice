//package com.programing.tech.oauth2;
//
//import com.programing.tech.entity.User;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Component;
//
//import java.util.*;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Builder
//@Component
//public class OAuth2UserDetailCustom implements UserDetails, OAuth2User {
//    private User user;
//    private Map<String, Object> attributes;
//    private Map<String, Object> additionalAttributes;
//
//
//    @Override
//    public <A> A getAttribute(String name) {
//        return OAuth2User.super.getAttribute(name);
//    }
//
//    @Override
//    public Map<String, Object> getAttributes() {
//        return attributes;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        if (user.getRoles().isEmpty()){
//            return new ArrayList<>();
//        }
//        return user.getRoles().stream().map(role->new SimpleGrantedAuthority(role.getName())).toList();
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return user.isAccountNonExpired();
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return user.isAccountNonLocked();
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return user.isCredentialsNonExpired();
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return user.isEnabled();
//    }
//
//    @Override
//    public String getName() {
//        return user.getUsername();
//    }
//}
