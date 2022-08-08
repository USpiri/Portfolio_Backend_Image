package com.portfolio.backenduspiri.security.repository;

import com.portfolio.backenduspiri.security.entity.Role;
import com.portfolio.backenduspiri.security.enums.ERole;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(ERole name);
}
