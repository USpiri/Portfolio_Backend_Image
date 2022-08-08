package com.portfolio.backenduspiri.service_interface;

import com.portfolio.backenduspiri.model.Experience;
import java.util.List;

public interface IExperienceService {
    
    public List<Experience> getExperience();
    
    public void createExperience( Experience exp );
    
    public void deleteExperience( Long id );
    
    public Experience getExperience( Long id );
    
    public List<Experience> findByPersonId( Long id );
    
    public Experience updateExperience( Experience exp );
    
}
