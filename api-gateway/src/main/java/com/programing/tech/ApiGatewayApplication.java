package com.programing.tech;

import com.programing.tech.entity.Role;
import com.programing.tech.entity.User;
import com.programing.tech.repository.RoleRepository;
import com.programing.tech.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
//khi dung ServerHttpSecurity khong duoc dung  @EnableWebSecurity
@RequiredArgsConstructor

public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner commandLineRunner(){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Role roleUser = new Role(1,"ROLE_ADMIN");
                Role roleAdmin = new Role(2,"ROLE_USER");
                List<Role> roles = List.of(roleRepository.save(roleUser),roleRepository.save(roleAdmin));

                User user = userRepository.save(User.builder().username("nguyenbangoc").password(passwordEncoder.encode("19112000"))
                        .accountNonExpired(true)
                        .accountNonLocked(true)
                        .credentialsNonExpired(true)
                        .isAccountNonExpired(true)
                        .enabled(true)
                        .build());
                user.setRoles(roles);
                userRepository.save(user);

            }
        };
    }

}
