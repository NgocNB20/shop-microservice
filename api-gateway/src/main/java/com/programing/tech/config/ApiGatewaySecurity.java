package com.programing.tech.config;





import com.programing.tech.service.JwtService;
import com.programing.tech.service.impl.CustomReactiveUserDetailsService;
import lombok.RequiredArgsConstructor;

import org.springframework.cloud.gateway.filter.factory.SetPathGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.web.client.RestClient;




@RequiredArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class ApiGatewaySecurity {
    private final String[] freeResourceUrls = {"/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/aggregate/**", "/eureka/**", "/api/auth/login"};
    private final JwtService jwtService;
    private final CustomReactiveUserDetailsService customReactiveUserDetailsService;
    private final JwtReactiveAuthenticationManager jwtReactiveAuthenticationManager;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(jwtReactiveAuthenticationManager);
        authenticationWebFilter.setServerAuthenticationConverter(new JwtServerAuthenticationConverter(jwtService,customReactiveUserDetailsService));
        http
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .logout(ServerHttpSecurity.LogoutSpec::disable)
                .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
                        .pathMatchers("/private/login","/product/**","/order/**").hasAnyRole("ADMIN")
                        .pathMatchers("/api/auth/**",  "/eureka/**").permitAll()
                        .anyExchange().authenticated());
        return http.build();
    }



//    @Bean
//    public ReactiveUserDetailsService userDetailsService() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("nguyenbangoc"))
//                .roles("USER1")
//                .build();
//        UserDetails user2 = User.builder()
//                .username("user1")
//                .password(passwordEncoder().encode("nguyenbangoc"))
//                .roles("ADMIN1")
//                .build();
//        return new MapReactiveUserDetailsService(user,user2);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, SetPathGatewayFilterFactory setPathGatewayFilterFactory) {
//        return builder.routes()
//                // Route cho service product
//                .route("product-com.programing.tech.service", r -> r
//                                .path("/api/v1/product")
//                                .uri("lb://product-com.programing.tech.service")
//                        //.filters(f -> f.rewritePath("/api/v1/product/(?<ignored>.*)", "/api/v1/product"))
//                )
//                // Route cho service order
//                .route("order-com.programing.tech.service", r -> r
//                        .path("/api/v1/order")
//                        .uri("lb://order-com.programing.tech.service")
//                )
//                // Route cho service payment
//                .route("payment-com.programing.tech.service", r -> r
//                        .path("/api/v1/payment")
//                        .uri("lb://payment-com.programing.tech.service")
//                )
//                // Route cho service inventory
//                .route("inventory-com.programing.tech.service", r -> r
//                        .path("/api/v1/inventory")
//                        .uri("lb://inventory-com.programing.tech.service")
//                )
//                // Route cho discovery (Eureka)
//                .route("discovery-com.programing.tech.service", r -> r
//                        .path("/eureka/web")
//                        .filters(f -> f.setPath("/"))
//                        .uri("http://localhost:8761")
//
//                )
//                // Route cho tài nguyên tĩnh của discovery
//                .route("discovery-com.programing.tech.service-static", r -> r
//                        .path("/eureka/**")
//                        .uri("http://localhost:8761")
//                )
//                .build();
//    }
}



//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .formLogin(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(req ->
//                        req.requestMatchers(freeResourceUrls)
//                                .permitAll()
//                                .anyRequest()
//                                .authenticated())
//                .userDetailsService(customUserDetailService);
//        return http.build();
//    }
//    @Bean
//    public AuthenticationManager authenticationManager() throws Exception {   /*load tất cả provider*/
//        return new AuthenticationManager() {
//            @Override
//            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//                return authentication;
//            }
//        };
//    }
//       private final CustomGlobalFilter customGlobalFilter;

//    @Bean
//    public MapReactiveUserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password(passwordEncoder().encode("nguyenbangoc"))
//                .roles("USER")
//                .build();
//        return new MapReactiveUserDetailsService(user);
//    }

//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http
//                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
//                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
//                .csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .logout(ServerHttpSecurity.LogoutSpec::disable)
////                .authenticationManager(customAuthenticationManager)
////                .securityContextRepository(securityContextRepository)
////                .addFilterAt(customGlobalFilter, SecurityWebFilterChain.class)
//                .authorizeExchange(authorizeExchangeSpec ->authorizeExchangeSpec
////                        .pathMatchers("/api/v1/**").hasAuthority("ROLE_USER")
//                            .pathMatchers("/api/auth/**","api/v1/**","/eureka/**").permitAll()
//                            .anyExchange().authenticated());
//        return http.build();
//    }




//    @Bean
//    public ReactiveAuthenticationManager authenticationManager(ReactiveUserDetailsService userDetailsService) {
//        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager =
//                new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
//        return authenticationManager;
//    }


//    @Bean
//    public ReactiveJwtDecoder reactiveJwtDecoder() {
//        return NimbusReactiveJwtDecoder.withJwkSetUri("https://www.googleapis.com/oauth2/v3/certs").build();
//    }

//    @Bean
//    public SecurityWebFilterChain customSecurityWebFilterChain(ServerHttpSecurity http,
//                                                               Oauth customOAuth2UserService,
//                                                               CustomLoginSuccessHandler authenticationSuccessHandler) {
//
//        return http
//                .authorizeExchange(exchanges -> exchanges
//                        .pathMatchers("/", "/login**", "/error**").permitAll()
//                        .anyExchange().authenticated()
//                )
//                .oauth2Login(oauth2 -> oauth2
//                        .userInfoEndpoint().userService(customOAuth2UserService)
//                        .and()
//                        .authenticationSuccessHandler(authenticationSuccessHandler)
//                )
//                .csrf().disable() // Disable CSRF for simplicity, but consider it in production
//                .build();
//    }
//    @Bean
//    public RedirectServerAuthenticationSuccessHandler authenticationSuccessHandler() {
//        RedirectServerAuthenticationSuccessHandler successHandler = new RedirectServerAuthenticationSuccessHandler();
//        successHandler.setLocation(URI.create("/api/v1/login/user"));
//        return successHandler;
//    }
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(List.of("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
//        configuration.setAllowedHeaders(List.of("*"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

//    private final CustomLoginSuccessHandler customLoginSuccessHandler;
//    private final String [] publicUrl = {"/api/v1/**"};
//
//    @Value("${jwt.key}")
//    private String jwtKey;
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(
//                User.withUsername("dvega")
//                        .password("{noop}password")
//                        .authorities("READ","ROLE_USER")
//                        .build());
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests( auth -> auth
//                        .requestMatchers("/api/auth/token").hasRole("USER")
//                        .anyRequest().hasAuthority("SCOPE_READ")
//                )
//                .authorizeHttpRequests(
//                        auth->auth.requestMatchers(publicUrl).permitAll()
//                        .anyRequest().authenticated())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .oauth2ResourceServer(
//                        (oauth2) -> oauth2.jwt(Customizer.withDefaults())
//                )
//                .oauth2Login(oauth2Login->oauth2Login.successHandler(customLoginSuccessHandler))
//
//                .httpBasic(withDefaults())
//                .build();
//    }

//    @Bean
//    JwtEncoder jwtEncoder() {
//        return new NimbusJwtEncoder(new ImmutableSecret<>(jwtKey.getBytes()));
//    }
//
//    @Bean
//    public JwtDecoder jwtDecoder() {
//        byte[] bytes = jwtKey.getBytes();
//        SecretKeySpec originalKey = new SecretKeySpec(bytes, 0, bytes.length,"RSA");
//        return NimbusJwtDecoder.withSecretKey(originalKey).macAlgorithm(MacAlgorithm.HS512).build();
//    }



