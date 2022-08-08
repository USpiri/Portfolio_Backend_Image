package com.portfolio.backenduspiri.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class JobType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size( max = 45 , message = "[JobType.name] - Not accurate size")
    private String name;

    public JobType() {
    }

    public JobType(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    
}
