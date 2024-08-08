package com.programing.tech.config;

import com.programing.tech.service.JwtService;
import com.programing.tech.service.impl.CustomReactiveUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

 
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


public class JwtServerAuthenticationConverter implements ServerAuthenticationConverter {

    private final JwtService jwtService;

    private final CustomReactiveUserDetailsService customReactiveUserDetailsService;

    public JwtServerAuthenticationConverter(JwtService jwtService, CustomReactiveUserDetailsService customReactiveUserDetailsService) {
        this.jwtService = jwtService;
        this.customReactiveUserDetailsService = customReactiveUserDetailsService;
    }

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            return Mono.empty();
        }

        // Extract the JWT token
        String jwtToken = token.substring(7);

        return Mono.just(jwtToken)
                .filter(jwtService::isTokenValid)  // Validate the JWT token
                .flatMap(validToken -> {
                    String username = jwtService.extractUsername(validToken); // Extract username from token
                    return Mono.justOrEmpty(username)
                                .flatMap(name -> customReactiveUserDetailsService.findByUsername(name)
                                    .flatMap(userDetails -> {
                                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                                userDetails, validToken, userDetails.getAuthorities());
                                        // Return the authToken or continue processing as needed
                                        return Mono.just(authToken);
                                    })
                            );
                });

    }
}
