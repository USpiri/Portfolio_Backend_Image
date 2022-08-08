package com.portfolio.backenduspiri.security.service;

import com.portfolio.backenduspiri.security.entity.Users;
import com.portfolio.backenduspiri.security.repository.UserRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    
    @Autowired
    UserRepository userRepo;
    
    public Optional<Users> getByUsername(String username){
        return userRepo.findByUsername(username);
    }
    
    public boolean existsByUsername(String username){
        return userRepo.existsByUsername(username);
    }
    
    public void save(Users user){
        userRepo.save(user);
    }
    
}
