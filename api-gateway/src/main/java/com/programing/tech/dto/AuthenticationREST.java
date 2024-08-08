//package com.programing.tech.dto;
//
//import com.programing.tech.entity.User;
//import com.programing.tech.repository.UserRepository;
//import com.programing.tech.service.JWTUtil;
//import com.programing.tech.service.impl.UserDetailServiceCustomImpl;
//import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Mono;
//import reactor.core.scheduler.Schedulers;
//
//@AllArgsConstructor
//@RestController
//@RequiredArgsConstructor
//public class AuthenticationREST {
//
//    private  JWTUtil jwtUtil;
//    private  PasswordEncoder passwordEncoder;
//    private  UserDetailServiceCustomImpl userService;
//    private  UserRepository userRepository;
//
//
//    @PostMapping("/login")
//    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest ar) {
//        return userService.findByUsername(ar.getUsername())
//                .filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword()))
//                .publishOn(Schedulers.boundedElastic())
//                .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userRepository.findByUsername(ar.getUsername()).get()))))
//                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
//    }
//}
