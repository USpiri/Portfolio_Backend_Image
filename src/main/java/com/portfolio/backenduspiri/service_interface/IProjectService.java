package com.portfolio.backenduspiri.service_interface;

import com.portfolio.backenduspiri.model.Project;
import java.util.List;

public interface IProjectService {
    
    public List<Project> getProjects();
    
    public void createProject( Project project );
    
    public void deleteProject( Long id );
    
    public Project getProject( Long id );
    
    public List<Project> findByPersonId( Long id );
    
    public Project updateProject( Project project );
    
}
