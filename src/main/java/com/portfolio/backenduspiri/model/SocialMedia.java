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
public class SocialMedia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size( max = 100 , message = "[SocialMedia.facebook] - Not accurate size")
    private String facebook;
    
    @Size( max = 100 , message = "[SocialMedia.twitter] - Not accurate size")
    private String twitter;
    
    @Size( max = 100 , message = "[SocialMedia.instagram] - Not accurate size")
    private String instagram;
    
    @Size( max = 100 , message = "[SocialMedia.github] - Not accurate size")
    private String github;
    
    @Size( max = 100 , message = "[SocialMedia.linkedin] - Not accurate size")
    private String linkedin;
    
    @Size( max = 100 , message = "[SocialMedia.cv] - Not accurate size")
    private String cv;
    
    @ManyToOne( fetch = FetchType.LAZY, optional = false )
    @JoinColumn( name = "person_id", nullable = false )
    @OnDelete( action = OnDeleteAction.CASCADE )
    @JsonIgnore
    private Person person;

    public SocialMedia() {
    }

    public SocialMedia(Long id, String facebook, String twitter, String instagram, String github, String linkedin, String cv, Person person) {
        this.id = id;
        this.facebook = facebook;
        this.twitter = twitter;
        this.instagram = instagram;
        this.github = github;
        this.linkedin = linkedin;
        this.cv = cv;
        this.person = person;
    }
    
}
