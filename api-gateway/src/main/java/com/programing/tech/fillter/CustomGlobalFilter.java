//package com.programing.tech.fillter;
//
//import com.programing.tech.service.JwtService;
//import com.programing.tech.service.impl.CustomUserDetailServiceImpl;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class CustomGlobalFilter implements GlobalFilter, Ordered {
//
//    private final JwtService jwtService;
//    private final CustomUserDetailServiceImpl customUserDetailService;
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        System.out.println("kakakaak");
//    return null;
//}
//
//
//    @Override
//    public int getOrder() {
//        return -1;
//    }
//}
