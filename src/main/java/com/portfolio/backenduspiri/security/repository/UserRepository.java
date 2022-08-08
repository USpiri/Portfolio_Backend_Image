package com.portfolio.backenduspiri.security.repository;

import com.portfolio.backenduspiri.security.entity.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
    boolean  existsByUsername(String username);
}
