package com.portfolio.backenduspiri.security.service;

import com.portfolio.backenduspiri.security.entity.Role;
import com.portfolio.backenduspiri.security.enums.ERole;
import com.portfolio.backenduspiri.security.repository.RoleRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RoleService {
    
    @Autowired
    RoleRepository roleRepo;
    
    public Optional<Role> getByRoleName(ERole roleName){
        return roleRepo.findByRoleName(roleName);
    }
    
    public void save(Role role){
        roleRepo.save(role);
    }
    
}
