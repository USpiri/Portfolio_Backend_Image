package com.portfolio.backenduspiri.repository;

import com.portfolio.backenduspiri.model.Experience;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    
    public List<Experience> findByPersonId( Long id );
    
}
