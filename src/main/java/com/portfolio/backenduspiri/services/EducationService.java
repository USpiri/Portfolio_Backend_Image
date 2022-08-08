package com.portfolio.backenduspiri.services;

import com.portfolio.backenduspiri.model.Education;
import com.portfolio.backenduspiri.repository.EducationRepository;
import com.portfolio.backenduspiri.service_interface.IEducationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationService implements IEducationService {
    
    @Autowired
    public EducationRepository eduRepo;

    @Override
    public List<Education> getEducation() {
        return eduRepo.findAll();
    }

    @Override
    public void createEducation(Education edu) {
        eduRepo.save(edu);
    }

    @Override
    public void deleteEducation(Long id) {
        eduRepo.deleteById(id);
    }

    @Override
    public Education getEducation(Long id) {
        return eduRepo.findById(id).orElse(null);
    }

    @Override
    public List<Education> findByPersonId(Long id) {
        return eduRepo.findByPersonId(id);
    }

    @Override
    public Education updateEducation(Education edu) {
        eduRepo.save(edu);
        return edu;
    }
    
}
