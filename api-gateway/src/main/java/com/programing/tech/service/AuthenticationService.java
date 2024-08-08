//package com.programing.tech.service;
//
//import com.programing.tech.config.CustomAuthenticationManager;
//import com.programing.tech.dto.TokenResponse;
//import com.programing.tech.dto.LoginRequest;
//import com.programing.tech.entity.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//@Component
//@RequiredArgsConstructor
//public class AuthenticationService {
//    private final JwtService jwtService;
//    private final CustomAuthenticationManager reactiveAuthenticationManager;
//
//    public Mono<TokenResponse> login(LoginRequest loginRequest) {
//        return reactiveAuthenticationManager.authenticate(
//                        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()))
//                .flatMap(auth -> {
//                    User userDetails = User.builder().username(auth.getPrincipal().toString()).build() ;
//                    String token = jwtService.generateToken(userDetails);
//                    String refreshToken = jwtService.generateRefreshToken(userDetails);
//                    return Mono.just(TokenResponse.builder().refreshToken(refreshToken).token(token).build());
//                });
//    }
//}
