package com.portfolio.backenduspiri.controller;

import com.portfolio.backenduspiri.model.Person;
import com.portfolio.backenduspiri.service_interface.IPersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
@CrossOrigin( origins = "https://uriel-spiridione.web.app" )
public class PersonController {
    
    @Autowired
    private IPersonService personService;
    
    @GetMapping
    @ResponseBody
    public List<Person> getPeople(){
        return personService.getPeople();
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    public Person getPerson( @PathVariable Long id ){
        return personService.getPerson( id );
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Person savePerson( @RequestBody Person per ){
        personService.createPerson(per);
        return personService.getPerson(per.getId());
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Person updatePerson( @PathVariable Long id, @RequestBody Person per ){
        Person perToUpdate = personService.getPerson(id);
        
        perToUpdate.setName(per.getName());
        perToUpdate.setSurname(per.getSurname());
        perToUpdate.setAddress(per.getAddress());
        perToUpdate.setBirth_date(per.getBirth_date());
        perToUpdate.setAge(per.getAge());
        perToUpdate.setPhone(per.getPhone());
        perToUpdate.setEmail(per.getEmail());
        perToUpdate.setLit_about(per.getLit_about());
        perToUpdate.setAbout(per.getAbout());
        
        return personService.updatePerson(perToUpdate);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deletePerson( @PathVariable Long id ){
        personService.deletePerson(id);
    }
    
}
