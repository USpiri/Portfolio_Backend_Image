package com.portfolio.backenduspiri.security.entity;

import com.portfolio.backenduspiri.security.enums.ERole;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private ERole roleName;

    public Role() {
    }

    public Role(ERole roleName) {
        this.roleName = roleName;
    }
    
}
