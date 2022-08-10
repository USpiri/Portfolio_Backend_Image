package com.portfolio.backenduspiri;

import com.portfolio.backenduspiri.model.Image;
import com.portfolio.backenduspiri.model.Person;
import com.portfolio.backenduspiri.model.SocialMedia;
import com.portfolio.backenduspiri.security.entity.Role;
import com.portfolio.backenduspiri.security.entity.Users;
import com.portfolio.backenduspiri.security.enums.ERole;
import com.portfolio.backenduspiri.security.service.RoleService;
import com.portfolio.backenduspiri.security.service.UserService;
import com.portfolio.backenduspiri.service_interface.IImageService;
import com.portfolio.backenduspiri.service_interface.IPersonService;
import com.portfolio.backenduspiri.service_interface.ISocialMediaService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BackendUspiriApplication {
    
    @Value("${portfolio.app.default.username}")
    private String defaultUsername;
    
    @Value("${portfolio.app.default.password}")
    private String defaultPassword;

    public static void main(String[] args) {
            SpringApplication.run(BackendUspiriApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner defaultData(IPersonService svcP, RoleService svcR, PasswordEncoder passEncoder, IImageService svcI, ISocialMediaService svcS, UserService svcU) {
        return args -> { 
            if (svcP.getPeople().isEmpty()) { 
                svcP.createPerson(new Person(1l, "", "", "", "", "", "", "", "", ""));
                svcI.createImage(new Image(1l, "", "", "", "", svcP.getPerson(1l)));
                svcS.createSocialMedia(new SocialMedia(1l, "", "", "", "", "", "", svcP.getPerson(1l)));
                svcR.save(new Role(ERole.ROLE_ADMIN));
                svcR.save(new Role(ERole.ROLE_USER));
                Users user = new Users(defaultUsername, passEncoder.encode(defaultPassword));
                Set<Role> roles = new HashSet<>();
                roles.add(svcR.getByRoleName(ERole.ROLE_USER).get());
                roles.add(svcR.getByRoleName(ERole.ROLE_ADMIN).get());
                user.setRoles(roles);
                svcU.save(user);
            }
        };
    }

}
