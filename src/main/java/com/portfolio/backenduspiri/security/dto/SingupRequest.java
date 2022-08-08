package com.portfolio.backenduspiri.security.dto;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SingupRequest {
    
    private String username;
    private String password;
    private Set<String> roles = new HashSet<>();
    
}
