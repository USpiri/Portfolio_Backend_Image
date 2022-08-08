package com.portfolio.backenduspiri.service_interface;

import com.portfolio.backenduspiri.model.Education;
import java.util.List;

public interface IEducationService {
    
    public List<Education> getEducation();
    
    public void createEducation (Education per);
    
    public void deleteEducation (Long id);
    
    public Education getEducation (Long id);
    
    public List<Education> findByPersonId(Long id);
    
    public Education updateEducation(Education edu);
    
}
