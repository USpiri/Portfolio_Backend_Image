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
public class Image {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String header;
    
    private String header_id;
    
    private String about;
    
    private String about_id;
    
    @ManyToOne( fetch = FetchType.LAZY, optional = false )
    @JoinColumn( name = "person_id", nullable = false )
    @OnDelete( action = OnDeleteAction.CASCADE )
    @JsonIgnore
    private Person person;

    public Image() {
    }

    public Image(Long id, String header, String header_id, String about, String about_id, Person person) {
        this.id = id;
        this.header = header;
        this.header_id = header_id;
        this.about = about;
        this.about_id = about_id;
        this.person = person;
    }

    
    
}
