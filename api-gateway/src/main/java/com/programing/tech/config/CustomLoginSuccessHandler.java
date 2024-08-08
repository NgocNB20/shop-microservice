//package com.programing.tech.config;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.server.WebFilterExchange;
//import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//import java.io.IOException;
//
//@Component
//public class CustomLoginSuccessHandler implements ServerAuthenticationSuccessHandler {
//
//    @Override
//    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
//
//        System.out.println(authentication.getPrincipal());
//        return null;
//    }
//}
