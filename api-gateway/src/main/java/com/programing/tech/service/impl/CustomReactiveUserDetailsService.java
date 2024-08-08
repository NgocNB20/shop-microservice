package com.programing.tech.service.impl;

import com.programing.tech.entity.User;

import com.programing.tech.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CustomReactiveUserDetailsService implements ReactiveUserDetailsService  {
    private final UserRepository userRepository;


    @Override
    public Mono<UserDetails> findByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("username not found"));
        return Mono.just(user);
    }
}
