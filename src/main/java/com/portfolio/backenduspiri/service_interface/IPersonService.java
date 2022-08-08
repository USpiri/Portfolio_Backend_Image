package com.portfolio.backenduspiri.service_interface;

import com.portfolio.backenduspiri.model.Person;
import java.util.List;

public interface IPersonService {
    
    public List<Person> getPeople();
    
    public void createPerson(Person per);
    
    public void deletePerson(Long id);
    
    public Person getPerson(Long id);
    
    public Person updatePerson(Person per);
    
}
