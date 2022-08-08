package com.portfolio.backenduspiri.repository;

import com.portfolio.backenduspiri.model.Project;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    public List<Project> findByPersonId( Long id );
    
}
