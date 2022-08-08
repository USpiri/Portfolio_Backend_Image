package com.portfolio.backenduspiri.services;

import com.portfolio.backenduspiri.model.Person;
import com.portfolio.backenduspiri.repository.PersonRepository;
import com.portfolio.backenduspiri.service_interface.IPersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IPersonService {
    
    @Autowired
    public PersonRepository personRepo;

    @Override
    public List<Person> getPeople() {
        return personRepo.findAll();
    }

    @Override
    public void createPerson(Person per) {
        personRepo.save(per);
    }

    @Override
    public void deletePerson(Long id) {
        personRepo.deleteById(id);
    }

    @Override
    public Person getPerson(Long id) {
        return personRepo.findById(id).orElse(null);
    }

    @Override
    public Person updatePerson(Person per) {
        personRepo.save(per);
        return per;
    }
    
}
