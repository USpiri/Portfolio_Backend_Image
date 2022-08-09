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
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size( max = 45 , message = "[Project.name] - Not accurate size")
    private String name;
    
    @Size( max = 100 , message = "[Project.description] - Not accurate size")
    private String description;
    
    private String img_url;
    private String imageId;
    
    @Size( max = 200 , message = "[Project.link] - Not accurate size")
    private String link;
    
    private Boolean enabled_link;
    
    @ManyToOne( fetch = FetchType.LAZY, optional = false )
    @JoinColumn( name = "person_id", nullable = false )
    @OnDelete( action = OnDeleteAction.CASCADE )
    @JsonIgnore
    private Person person;

    public Project() {
    }

    public Project(Long id, String name, String description, String img_url, String imageId, String link, Boolean enabled_link, Person person) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.img_url = img_url;
        this.imageId = imageId;
        this.link = link;
        this.enabled_link = enabled_link;
        this.person = person;
    }

    
    
}
