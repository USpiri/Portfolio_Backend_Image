package com.portfolio.backenduspiri.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter @Setter
@Entity
public class Skill {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size( max = 45 , message = "[Skill.name] - Not accurate size")
    private String name;
    
    @Size( max = 10 , message = "[Skill.percentage] - Not accurate size")
    private String percentage;
    
    @Size( max = 45 , message = "[Skill.icon] - Not accurate size")
    private String icon;
    
    @ManyToOne( fetch = FetchType.LAZY, optional = false )
    @JoinColumn( name = "person_id", nullable = false )
    @OnDelete( action = OnDeleteAction.CASCADE )
    @JsonIgnore
    private Person person;

    public Skill() {
    }

    public Skill(Long id, String name, String percentage, String icon) {
        this.id = id;
        this.name = name;
        this.percentage = percentage;
        this.icon = icon;
    }
    
}
