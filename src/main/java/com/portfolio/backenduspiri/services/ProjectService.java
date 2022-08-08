package com.portfolio.backenduspiri.services;

import com.portfolio.backenduspiri.model.Project;
import com.portfolio.backenduspiri.repository.ProjectRepository;
import com.portfolio.backenduspiri.service_interface.IProjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService implements IProjectService {
    
    @Autowired
    public ProjectRepository projectRepo;

    @Override
    public List<Project> getProjects() {
        return projectRepo.findAll();
    }

    @Override
    public void createProject(Project project) {
        projectRepo.save(project);
    }

    @Override
    public void deleteProject(Long id) {
        projectRepo.deleteById(id);
    }

    @Override
    public Project getProject(Long id) {
        return projectRepo.findById(id).orElse(null);
    }

    @Override
    public List<Project> findByPersonId(Long id) {
        return projectRepo.findByPersonId(id);
    }

    @Override
    public Project updateProject(Project project) {
        projectRepo.save(project);
        return project;
    }
    
}
