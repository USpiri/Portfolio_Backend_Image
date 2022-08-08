package com.portfolio.backenduspiri.security.controller;

import com.portfolio.backenduspiri.security.dto.JwtDTO;
import com.portfolio.backenduspiri.security.dto.LoginRequest;
import com.portfolio.backenduspiri.security.dto.SingupRequest;
import com.portfolio.backenduspiri.security.entity.Role;
import com.portfolio.backenduspiri.security.entity.Users;
import com.portfolio.backenduspiri.security.enums.ERole;
import com.portfolio.backenduspiri.security.jwt.JwtProvider;
import com.portfolio.backenduspiri.security.service.RoleService;
import com.portfolio.backenduspiri.security.service.UserService;
import com.portfolio.backenduspiri.security.util.Message;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    
    @Autowired
    PasswordEncoder passEncoder;
    
    @Autowired
    AuthenticationManager authManager;
    
    @Autowired
    UserService userService;
    
    @Autowired
    RoleService roleService;
    
    @Autowired
    JwtProvider jwtProvider;
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/singup")
    public ResponseEntity<?> singup(@Valid @RequestBody SingupRequest singupRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Message("[AuthController] - Invalid Credentials"),HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByUsername(singupRequest.getUsername())) {
            return new ResponseEntity(new Message("[AuthController] - Username alredy exist"),HttpStatus.BAD_REQUEST);
        }
        Users user = new Users(singupRequest.getUsername(), passEncoder.encode(singupRequest.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(ERole.ROLE_USER).get());
        if (singupRequest.getRoles().contains("admin")) {
            roles.add(roleService.getByRoleName(ERole.ROLE_ADMIN).get());
        }
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity(new Message("Created new User"), HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Message("Unvalid Fields"), HttpStatus.BAD_REQUEST);
        }
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = jwtProvider.generateToken(auth);
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDTO, HttpStatus.OK);
    }
    
}
