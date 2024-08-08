//package com.programing.tech.config;
//
//import com.programing.tech.service.JWTUtil;
//import com.programing.tech.service.JwtService;
//import com.programing.tech.service.impl.CustomReactiveUserDetailsService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.ReactiveAuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//@Component
//@RequiredArgsConstructor
//public class CustomAuthenticationManager implements ReactiveAuthenticationManager {
//
//    private final CustomReactiveUserDetailsService userDetailsService;
//    private final JwtService jwtService;
//
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public Mono<Authentication> authenticate(Authentication authentication) throws AuthenticationException {
//        String token = authentication.getCredentials().toString();
//        String username = jwtService.getUsernameFromToken(token);
//
//
//        return userDetailsService.findByUsername(username)
//                .map(userDetails -> {
//
//                    if (jwtService.isTokenValid(token,userDetails)) {
//                        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
//                    } else {
//                        throw new BadCredentialsException("Invalid credentials");
//                    }
//                });
//    }
//}
//
