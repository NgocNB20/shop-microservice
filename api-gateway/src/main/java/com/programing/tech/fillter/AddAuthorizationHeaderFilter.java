//package com.programing.tech.fillter;
//
//import com.programing.tech.service.JwtService;
//import com.programing.tech.service.impl.UserDetailServiceCustomImpl;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.ReactiveSecurityContextHolder;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Mono;
//
//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class AddAuthorizationHeaderFilter implements WebFilter  {
//    private final JwtService jwtService;
//    private final Re userDetailsService;
//
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//        String jwt = exactAuthorization(exchange);
//        log.info("JWT: {}",jwt);
//
//        String path = exchange.getRequest().getPath().toString();
//        log.info("Path: {}",path);
//
//        if (path.contains("/api/auth")) {
//            return chain.filter(exchange);
//        }
//        String userEmail = jwtService.extractUsername(jwt);
//
//        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            return this.userDetailsService.findByUsername(userEmail)
//                    .filter(userDetails -> jwtService.isTokenValid(jwt, userDetails))
//                    .flatMap(userDetails -> {
//                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                                userDetails,
//                                null,
//                                userDetails.getAuthorities()
//                        );
//                        return chain.filter(exchange)
//                                .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authToken));
//                    })
//                    .switchIfEmpty(chain.filter(exchange)); // Nếu không tìm thấy UserDetails hoặc token không hợp lệ
//        } else {
//            return chain.filter(exchange); // Nếu không có email hoặc đã có authentication
//        }
//    }
//
//    private String exactAuthorization(ServerWebExchange exchange) {
//        String authorizationHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            return authorizationHeader.substring(7);
//        }
//        return null;
//    }
//}
