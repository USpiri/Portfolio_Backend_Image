package com.portfolio.backenduspiri.repository;

import com.portfolio.backenduspiri.model.Education;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
    
    public List<Education> findByPersonId( Long id );
    
}
