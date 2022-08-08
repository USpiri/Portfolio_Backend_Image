package com.portfolio.backenduspiri.services;

import com.portfolio.backenduspiri.model.Experience;
import com.portfolio.backenduspiri.repository.ExperienceRepository;
import com.portfolio.backenduspiri.service_interface.IExperienceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienceService implements IExperienceService {
    
    @Autowired
    public ExperienceRepository expRepo;

    @Override
    public List<Experience> getExperience() {
        return expRepo.findAll();
    }

    @Override
    public void createExperience(Experience exp) {
        expRepo.save(exp);
    }

    @Override
    public void deleteExperience(Long id) {
        expRepo.deleteById(id);
    }

    @Override
    public Experience getExperience(Long id) {
        return expRepo.findById(id).orElse(null);
    }

    @Override
    public List<Experience> findByPersonId(Long id) {
        return expRepo.findByPersonId(id);
    }

    @Override
    public Experience updateExperience(Experience exp) {
        expRepo.save(exp);
        return exp;
    }
    
    
    
}
