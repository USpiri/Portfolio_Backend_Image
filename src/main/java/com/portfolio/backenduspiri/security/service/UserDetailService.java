package com.portfolio.backenduspiri.security.service;

import com.portfolio.backenduspiri.security.entity.PrincipalUser;
import com.portfolio.backenduspiri.security.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userService.getByUsername(username).get();
        return PrincipalUser.build(user);
    }
    
}
