package com.programing.tech.controller;


import com.programing.tech.dto.AuthResponse;
import com.programing.tech.dto.BaseResponse;
import com.programing.tech.dto.LoginRequest;
import com.programing.tech.repository.UserRepository;
import com.programing.tech.service.JwtService;
import lombok.RequiredArgsConstructor;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Mono;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;


@RestController
@RequiredArgsConstructor
public class LoginController {

    private final JwtService jwtService;
    private final ReactiveUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/private/login")
    public Mono<String> welcome() {
        return Mono.just("well come to login");
    }

    @PostMapping("api/auth/login")
    public Mono<BaseResponse> login(@RequestBody LoginRequest loginRequest) {
        return userDetailsService.findByUsername(loginRequest.getUsername())
                .flatMap(userDetails -> {
                    if (passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
                        String jwtToken = jwtService.generateToken(userDetails);
                        String refreshToken = jwtService.generateRefreshToken(userDetails);
                        AuthResponse authResponse = AuthResponse.builder()
                                .token(jwtToken)
                                .refreshToken(refreshToken)
                                .build();

                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userDetails.getUsername(), null, userDetails.getAuthorities());

                        // Create a new SecurityContext and set the Authentication object
                        SecurityContext securityContext = new SecurityContextImpl(authToken);

                        // Store the SecurityContext in the ReactiveSecurityContextHolder
                        return Mono.just(BaseResponse.builder().data(authResponse).build())
                                .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(securityContext)));
                    } else {
                        return Mono.error(new AuthenticationException("Invalid credentials") {});
                    }
                });
    }
}




