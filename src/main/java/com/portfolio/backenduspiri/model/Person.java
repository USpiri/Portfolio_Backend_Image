package com.portfolio.backenduspiri.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size( max = 45 , message = "[Person.name] - Not accurate size")
    private String name;
    
    @Size( max = 45 , message = "[Person.surname] - Not accurate size")
    private String surname;
    
    @Size( max = 45 , message = "[Person.address] - Not accurate size")
    private String address;
    
    @Size( max = 45 , message = "[Person.birth_date] - Not accurate size")
    private String birth_date;
    
    @Size( max = 10 , message = "[Person.age] - Not accurate size")
    private String age;
    
    @Size( max = 25 , message = "[Person.phone] - Not accurate size")
    private String phone;
    
    @Size( max = 45 , message = "[Person.email] - Not accurate size")
    @Email
    private String email;
    
    @Size( max = 80 , message = "[Person.lit_about] - Not accurate size")
    private String lit_about;
    
    @Size( max = 200 , message = "[Person.about] - Not accurate size")
    private String about;

    public Person() {
    }

    public Person(Long id, String name, String surname, String address, String birth_date, String age, String phone, String email, String lit_about, String about) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.birth_date = birth_date;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.lit_about = lit_about;
        this.about = about;
    }
    
}
