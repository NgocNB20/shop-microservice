package com.programing.tech.repository;

import com.programing.tech.entity.Provider;
import com.programing.tech.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndProviderId(String username, String provider);
    Optional<User> findByUsername(String username);
}
