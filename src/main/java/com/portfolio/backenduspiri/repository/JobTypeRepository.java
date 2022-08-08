package com.portfolio.backenduspiri.repository;

import com.portfolio.backenduspiri.model.JobType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTypeRepository extends JpaRepository<JobType, Long> {
    
}
