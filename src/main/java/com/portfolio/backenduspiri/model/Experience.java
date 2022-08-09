package com.portfolio.backenduspiri.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter @Setter
@Entity
public class Experience {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size( max = 45 , message = "[Experience.company] - Not accurate size")
    private String company;
    
    @Size( max = 45 , message = "[Experience.job] - Not accurate size")
    private String job;
    
    private Boolean is_actual;
    
    @Size( max = 25 , message = "[Experience.start_date] - Not accurate size")
    private String start_date;
    
    @Size( max = 25 , message = "[Experience.end_date] - Not accurate size")
    private String end_date;
    
    private String img_url;
    private String imageId;
    
    @Size( max = 200 , message = "[Experience.link] - Not accurate size")
    private String link;
    
    private Boolean enabled_link;
    
    private String job_type;
    
    @ManyToOne( fetch = FetchType.LAZY, optional = false )
    @JoinColumn( name = "person_id", nullable = false )
    @OnDelete( action = OnDeleteAction.CASCADE )
    @JsonIgnore
    private Person person;

    public Experience() {
    }

    public Experience(Long id, String company, String job, Boolean is_actual, String start_date, String end_date, String img_url, String imageId, String link, Boolean enabled_link, String job_type, Person person) {
        this.id = id;
        this.company = company;
        this.job = job;
        this.is_actual = is_actual;
        this.start_date = start_date;
        this.end_date = end_date;
        this.img_url = img_url;
        this.imageId = imageId;
        this.link = link;
        this.enabled_link = enabled_link;
        this.job_type = job_type;
        this.person = person;
    }

    
    
}
