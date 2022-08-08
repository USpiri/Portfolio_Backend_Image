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
public class Education {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size( max = 100 , message = "[Education.where] - Not accurate size")
    private String where;
    
    @Size( max = 45 , message = "[Education.degree] - Not accurate size")
    private String degree;
    
    private Boolean is_actual;
    
    @Size( max = 25 , message = "[Education.start_date] - Not accurate size")
    private String start_date;
    
    @Size( max = 25 , message = "[Education.end_date] - Not accurate size")
    private String end_date;
    
    @Size( max = 200 , message = "[Education.img_url] - Not accurate size")
    private String img_url;
    
    @Size( max = 100 , message = "[Education.link] - Not accurate size")
    private String link;
    
    private Boolean enabled_link;
    
    @ManyToOne( fetch = FetchType.LAZY, optional = false )
    @JoinColumn( name = "person_id", nullable = false )
    @OnDelete( action = OnDeleteAction.CASCADE )
    @JsonIgnore
    private Person person;

    public Education() {
    }

    public Education(Long id, String where, String degree, Boolean is_actual, String start_date, String end_date, String img_url, String link, Boolean enabled_link) {
        this.id = id;
        this.where = where;
        this.degree = degree;
        this.is_actual = is_actual;
        this.start_date = start_date;
        this.end_date = end_date;
        this.img_url = img_url;
        this.link = link;
        this.enabled_link = enabled_link;
    }
    
}
